
angular.module('checklist-model', [])
.directive('checklistModel', ['$parse', '$compile', function($parse, $compile) {
  // contains
  function contains(arr, item, comparator) {
    if (angular.isArray(arr)) {
      for (var i = arr.length; i--;) {
        if (comparator(arr[i], item)) {
          return true;
        }
      }
    }
    return false;
  }

  // add
  function add(arr, item, comparator) {
    arr = angular.isArray(arr) ? arr : [];
      if(!contains(arr, item, comparator)) {
          arr.push(item);
      }
    return arr;
  }  

  // remove
  function remove(arr, item, comparator) {
    if (angular.isArray(arr)) {
      for (var i = arr.length; i--;) {
        if (comparator(arr[i], item)) {
          arr.splice(i, 1);
          break;
        }
      }
    }
    return arr;
  }

  // http://stackoverflow.com/a/19228302/1458162
  function postLinkFn(scope, elem, attrs) {
     // exclude recursion, but still keep the model
    var checklistModel = attrs.checklistModel;
    attrs.$set("checklistModel", null);
    // compile with `ng-model` pointing to `checked`
    $compile(elem)(scope);
    attrs.$set("checklistModel", checklistModel);

    // getter / setter for original model
    var getter = $parse(checklistModel);
    var setter = getter.assign;
    var checklistChange = $parse(attrs.checklistChange);
    var checklistBeforeChange = $parse(attrs.checklistBeforeChange);

    // value added to list
    var value = attrs.checklistValue ? $parse(attrs.checklistValue)(scope.$parent) : attrs.value;


    var comparator = angular.equals;

    if (attrs.hasOwnProperty('checklistComparator')){
      if (attrs.checklistComparator[0] == '.') {
        var comparatorExpression = attrs.checklistComparator.substring(1);
        comparator = function (a, b) {
          return a[comparatorExpression] === b[comparatorExpression];
        };
        
      } else {
        comparator = $parse(attrs.checklistComparator)(scope.$parent);
      }
    }

    // watch UI checked change
    scope.$watch(attrs.ngModel, function(newValue, oldValue) {
      if (newValue === oldValue) { 
        return;
      } 

      if (checklistBeforeChange && (checklistBeforeChange(scope) === false)) {
        scope[attrs.ngModel] = contains(getter(scope.$parent), value, comparator);
        return;
      }

      setValueInChecklistModel(value, newValue);

      if (checklistChange) {
        checklistChange(scope);
      }
    });

    function setValueInChecklistModel(value, checked) {
      var current = getter(scope.$parent);
      if (angular.isFunction(setter)) {
        if (checked === true) {
          setter(scope.$parent, add(current, value, comparator));
        } else {
          setter(scope.$parent, remove(current, value, comparator));
        }
      }
      
    }

    // declare one function to be used for both $watch functions
    function setChecked(newArr, oldArr) {
      if (checklistBeforeChange && (checklistBeforeChange(scope) === false)) {
        setValueInChecklistModel(value, scope[attrs.ngModel]);
        return;
      }
      scope[attrs.ngModel] = contains(newArr, value, comparator);
    }

    // watch original model change
    // use the faster $watchCollection method if it's available
    if (angular.isFunction(scope.$parent.$watchCollection)) {
        scope.$parent.$watchCollection(checklistModel, setChecked);
    } else {
        scope.$parent.$watch(checklistModel, setChecked, true);
    }
  }

  return {
    restrict: 'A',
    priority: 1000,
    terminal: true,
    scope: true,
    compile: function(tElement, tAttrs) {
      if ((tElement[0].tagName !== 'INPUT' || tAttrs.type !== 'checkbox') && (tElement[0].tagName !== 'MD-CHECKBOX') && (!tAttrs.btnCheckbox)) {
        throw 'checklist-model should be applied to `input[type="checkbox"]` or `md-checkbox`.';
      }

      if (!tAttrs.checklistValue && !tAttrs.value) {
        throw 'You should provide `value` or `checklist-value`.';
      }

      // by default ngModel is 'checked', so we set it if not specified
      if (!tAttrs.ngModel) {
        // local scope var storing individual checkbox model
        tAttrs.$set("ngModel", "checked");
      }

      return postLinkFn;
    }
  };
}]);

var underscore = angular.module('underscore', []);
	underscore.factory('_', function() {
		return window._; // assumes underscore has already been loaded on the page
	});  


	var app=angular.module("myzfApp",['underscore',"checklist-model",'ngRoute','ui.bootstrap']);
	


app.directive('asDate', function () {
    return {
        require: '^ngModel',
        restrict: 'A',
        link: function (scope, element, attrs, ctrl) {
            ctrl.$formatters.splice(0, ctrl.$formatters.length);
            ctrl.$parsers.splice(0, ctrl.$parsers.length);
            ctrl.$formatters.push(function (modelValue) {
                if (!modelValue) {
                    return;
                }
                return new Date(modelValue);
            });
            ctrl.$parsers.push(function (modelValue) {
                return modelValue;
            });
        }
    };
});

app.directive('loader', function () {
    return {
        restrict: 'A',
        scope: {cond: '=loader'},
        template: '<span ng-if="isLoading()" class="soft"><span class="fa fa-refresh fa-spin"></span></span>',
        link: function (scope) {
            scope.isLoading = function() {
                var ret = scope.cond === true || (
                        scope.cond &&
                        scope.cond.$$state &&
                        angular.isDefined(scope.cond.$$state.status) &&
                        scope.cond.$$state.status === 0
                    );
                return ret;
            }
        }
    };
}); 

app.directive('myDirective', function() {
	  return {
	    require: 'ngModel',
	    link: function(scope, element, attr, mCtrl) {
	      function myValidation(value) {
	        if ((!isNaN(value) && angular.isNumber(value)) || value==null ) {
	          mCtrl.$setValidity('charE', true);
	        } else {
	          mCtrl.$setValidity('charE', false);
	        }
	        return value;
	      }
	      mCtrl.$parsers.push(myValidation);
	    }
	  };
	});

app.controller('DatepickerDemoCtrl', function ($scope) {
	  $scope.today = function() {
	    $scope.dt = new Date();
	  };
	  $scope.today();

	  $scope.clear = function () {
	    $scope.dt = null;
	  };

	  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };

	  $scope.toggleMin = function() {
	    $scope.minDate = $scope.minDate ? null : new Date();
	  };
	  $scope.toggleMin();
	  $scope.maxDate = new Date(2020, 5, 22);

	  $scope.open = function($event) {
	    $scope.status.opened = true;
	  };

	  $scope.setDate = function(year, month, day) {
	    $scope.dt = new Date(year, month, day);
	  };

	  $scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	  };

	  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'MM/dd/yyyy' , 'dd.MM.yyyy', 'shortDate'];
	  $scope.format = $scope.formats[2];

	  $scope.status = {
	    opened: false
	  };

	  var tomorrow = new Date();
	  tomorrow.setDate(tomorrow.getDate() + 1);
	  var afterTomorrow = new Date();
	  afterTomorrow.setDate(tomorrow.getDate() + 2);
	  $scope.events =
	    [
	      {
	        date: tomorrow,
	        status: 'full'
	      },
	      {
	        date: afterTomorrow,
	        status: 'partially'
	      }
	    ];

	  $scope.getDayClass = function(date, mode) {
	    if (mode === 'day') {
	      var dayToCheck = new Date(date).setHours(0,0,0,0);

	      for (var i=0;i<$scope.events.length;i++){
	        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

	        if (dayToCheck === currentDay) {
	          return $scope.events[i].status;
	        }
	      }
	    }

	    return '';
	  };
	});

app.controller("reportDechargeController",function($scope,_,$http,collectionAprinter,sharedLicence) {
	
	$scope.sharedLicence=sharedLicence.sharedLicence;
	$scope.collectionAprinter = collectionAprinter.collectionAprinter;
	
	 $scope.printDiv = function (divName) {

		    var printContents = document.getElementById(divName).innerHTML;
		    var originalContents = document.body.innerHTML;      

		    if (navigator.userAgent.toLowerCase().indexOf('chrome') > -1) {
		        var popupWin = window.open('', '_blank', 'width=600,height=600,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		        popupWin.window.focus();
		        popupWin.document.write('<!DOCTYPE html><html lang="fr"><head>   <meta charset="utf-8">  <meta name="robots" content="noindex">'+
		        	    '<link href="assets/css/bootstrap.css" rel="stylesheet" />'+
		        	       '<link href="assets/css/font-awesome.css" rel="stylesheet" />'+
		        	       '<link href="js/angucomplete-alt.css" rel="stylesheet" />'+
		        	       ' <link href="assets/css/custom.css" rel="stylesheet" />'+
		        	    '  <link href="css/print.css" rel="stylesheet" />'+
		        	     '  <link href="css/style1.css" rel="stylesheet" />'+
		            '<title>Simple Invoice - Bootsnipp.com</title> <meta name="viewport" content="width=device-width, initial-scale=1">'+
		             '<style type="text/css"> .invoice-title h2, .invoice-title h3 { display: inline-block; }'+
		           ' .table > tbody > tr > .no-line { border-top: none;} .table > thead > tr > .no-line { border-bottom: none; }'+
		            ' .table > tbody > tr > .thick-line {  border-top: 2px solid; }</style></head>'+
		            '<body onload="window.print()"><div class="reward-body">' + printContents + '</div></html>');
		        popupWin.onbeforeunload = function (event) {
		            popupWin.close();
		            return '.\n';
		        };
		        popupWin.onabort = function (event) {
		            popupWin.document.close();
		            popupWin.close();
		        }
		    } else {
		        var popupWin = window.open('', '_blank', 'width=800,height=600');
		        popupWin.document.open();
		        popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="style.css" /></head><body onload="window.print()">' + printContents + '</html>');
		        popupWin.document.close();
		    }
		    popupWin.document.close();

		    return true;
		}
	
});

app.controller("reportQuiticeController",function($scope,_,$http,quiticeAprinter,sharedLicence) {
	
	$scope.sharedLicence=sharedLicence.sharedLicence;
	$scope.quiticeAprinter = quiticeAprinter.quiticeAprinter;
	
	 $scope.printDiv = function (divName) {

		    var printContents = document.getElementById(divName).innerHTML;
		    var originalContents = document.body.innerHTML;      

		    if (navigator.userAgent.toLowerCase().indexOf('chrome') > -1) {
		        var popupWin = window.open('', '_blank', 'width=600,height=600,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		        popupWin.window.focus();
		        popupWin.document.write('<!DOCTYPE html><html lang="fr"><head>   <meta charset="utf-8">  <meta name="robots" content="noindex">'+
		        	    '<link href="assets/css/bootstrap.css" rel="stylesheet" />'+
		        	       '<link href="assets/css/font-awesome.css" rel="stylesheet" />'+
		        	       '<link href="js/angucomplete-alt.css" rel="stylesheet" />'+
		        	       ' <link href="assets/css/custom.css" rel="stylesheet" />'+
		        	    '  <link href="css/print.css" rel="stylesheet" />'+
		        	     '  <link href="css/style1.css" rel="stylesheet" />'+
		            '<title>Simple Invoice - Bootsnipp.com</title> <meta name="viewport" content="width=device-width, initial-scale=1">'+
		             '<style type="text/css"> .invoice-title h2, .invoice-title h3 { display: inline-block; }'+
		           ' .table > tbody > tr > .no-line { border-top: none;} .table > thead > tr > .no-line { border-bottom: none; }'+
		            ' .table > tbody > tr > .thick-line {  border-top: 2px solid; }</style></head>'+
		            '<body onload="window.print()"><div class="reward-body">' + printContents + '</div></html>');
		        popupWin.onbeforeunload = function (event) {
		            popupWin.close();
		            return '.\n';
		        };
		        popupWin.onabort = function (event) {
		            popupWin.document.close();
		            popupWin.close();
		        }
		    } else {
		        var popupWin = window.open('', '_blank', 'width=800,height=600');
		        popupWin.document.open();
		        popupWin.document.write('<html><head><link rel="stylesheet" type="text/css" href="style.css" /></head><body onload="window.print()">' + printContents + '</html>');
		        popupWin.document.close();
		    }
		    popupWin.document.close();

		    return true;
		}
	
});

app.factory('collectionAprinter', function($rootScope){
	collectionAprinter= {};
	
	collectionAprinter.fillin=function(toAdd){
		this.collectionAprinter=toAdd;
	}
	

	  return collectionAprinter;
});

app.factory('quiticeAprinter', function($rootScope){
	quiticeAprinter= {};
	
	quiticeAprinter.fillin=function(toAdd){
		this.quiticeAprinter=toAdd;
	}
	

	  return quiticeAprinter;
});
app.factory('sharedLicence', function ($rootScope) {
    var sharedLicence = {};
    sharedLicence.fillin=function(toAdd){
		this.sharedLicence=toAdd;
	}
   return sharedLicence;
});
app.controller("myzfController",function($scope,_,$http,$location,$sce,collectionAprinter,sharedLicence) {
	
	$scope.sharedLicence=sharedLicence.sharedLicence;
	  
	 $scope.urldecharge ="";
	 $scope.exception={message:null};
	 $scope.filter= {};
	 $scope.collecteurList= [];
	 $scope.newCarnet=false;
	 $scope.newCarnetR=false;
	 $scope.carnetDecharge=null;
	 $scope.carnetRetour=null;
	 $scope.val=null;
	 $scope.carnetsDechargeList  = [];
	 $scope.carnetsRetourList = [];
	 $scope.pageCollectionsList=[];
	 $scope.pageCourante=0;
	 $scope.pageSize=20;
	 $scope.collectionPorte=null;
	 $scope.pages=[];
	 $scope.codeCollecteur1=null;
	 $scope.currentIdDecharge=null;
	
	 $scope.options2 = [];
	 $scope.listCrnDechForCurrCol=[];
	 $scope.listNPagesLibre=[];
	 
	 
		$scope.finderloader=true;
		
		 $scope.isLoading = function() {
		        return $http.pendingRequests.length > 0;
		    };
	
	
	 $scope.openTab = function(item) {
		   
		 collectionAprinter.fillin(item);
		 console.log("ggg");
		 console.log(item);
		 $location.path('reportDecharge');
		    $scope.urldecharge = '#/reportDecharge';
		}
		$scope.filtrerEncours=function(){
			console.log($scope.filter.journee);
			
			$http.post("collectionsEncours?page="+$scope.pageCourante+"&size="+$scope.pageSize,$scope.filter)
			.success(function(data){
				$scope.pageCollectionsList=data;
				$scope.pages=new Array(data.totalPages);
				
				console.log("collectionsEncours?page="+$scope.pageCourante+"&size="+$scope.pageSize,$scope.filter);
			})
			
			.error(function(data) {
				$scope.exception.message=data.message;
				console.log("error is "+data.message);
			});
		}
	 
	 $scope.chfkCarnet= function(item) {
		 console.log("ttt");
	     console.log(item);
		 $scope.carnetRetour={numeroCarnet:item.numeroCarnet,groupe:item.groupe};
		 $scope.carnetRetour.nCarnet=item.numeroCarnet;
		 $scope.carnetRetour.nGroupe=item.groupe;
		 console.log($scope.carnetRetour.nCarnet);
		 console.log($scope.carnetRetour.nGroupe);
		 $scope.listNPagesLibre=[];
		 console.log("listNumPagesLibres?gr="+item.groupe+"&carnet="+item.numeroCarnet+"&currcol="+$scope.currentCodeCollectionPorte);
		 	 $http.get("listNumPagesLibres?gr="+item.groupe+"&carnet="+item.numeroCarnet+"&currcol="+$scope.currentCodeCollectionPorte)
			.success(function(data){
				
							console.log(_.pluck(data,"numero"));
				
							$scope.listNPagesLibre =_.pluck(data,"numero");
							$scope.finderloader=false;
			
			});
	 }
	 
	 $scope.chcurrentCodeCollectionPorte= function(currentCodeCollectionPorte){
		
	 }
	 $scope.chCodeCollecteur1= function(item){
		 console.log("code collecteur 1");
		 $scope.codeCollecteur1=item;
		 console.log(item);
	 }
	 $scope.alert= function(itemToAdd) {
		 console.log("list de carnets a decharger");
			$http.get("carnetADecharger?gr="+itemToAdd)
			.success(function(data){
				
				console.log(_.pluck(data,"numeroCarnet"));
				
				$scope.options2 =_.pluck(data,"numeroCarnet");
				$scope.finderloader=false;
			});
	 }
	
	 $scope.carnetsDechargeToAdd = [{
		    nCarnet: '',
		    nGroupe: ''
		  }];
	 
	 $scope.carnetsRetourToAdd = [{
		    numeroCarnet: '',
		    groupe: ''
		  }];
	 $scope.addCarnetsDecharge = function(itemToAdd) {
		 $scope.newCarnet=false;
		    var index = $scope.carnetsDechargeToAdd.indexOf(itemToAdd);

		    $scope.carnetsDechargeToAdd.splice(index, 1);

		    $scope.carnetsDechargeList.push(angular.copy(itemToAdd));
		  
		  
		    console.log(_.pluck($scope.carnetsDechargeList,"nCarnet").toString());
		  };
		  
	  
			 $scope.addCarnetsRetour = function(itemToAddR) {
				 $scope.newCarnetR=false;
				    var index = $scope.carnetsRetourToAdd.indexOf(itemToAddR);

				    $scope.carnetsRetourToAdd.splice(index, 1);

				    $scope.carnetsRetourList.push(angular.copy(itemToAddR));
				  
				  
				    console.log(_.pluck($scope.carnetsRetourList,"nCarnet").toString());
				  };
				  
		  $scope.remove = function(item) { 
			  var index = $scope.carnetsDechargeList.indexOf(item);
			  $scope.carnetsDechargeList.splice(index, 1);     
			}
		  $scope.removeR = function(item) { 
			  var index = $scope.carnetsRetourList.indexOf(item);
			  $scope.carnetsRetourList.splice(index, 1);     
			}
     
     
	$scope.collecteur=null;
	$scope.recettes=0;
	$scope.collectionPorte=null;
	$scope.codeCollecteur=null;
	$scope.operation={type:"decharges"};
	$scope.currentCodeCollectionPorte=-1;
	
	$http.get("collecteursList")
	.success(function(data){
		$scope.collecteurList=data;
	    console.log($scope.collecteurList);
	});
	$scope.chargerCollecteur=function(){
		$http.get("collecteursList")
		.success(function(data){
			$scope.collecteur=data;
			$scope.chargerCollections();
		});
	};
	
	$scope.goToPage=function(p){
		$scope.pageCourante=p;
		$scope.chargerCollections();
	}
	$scope.goToRetour=function(idcollection){
		$scope.currentCodeCollectionPorte=idcollection;
		
		$scope.operation={type:"retours"};
		$http.get("getListCarnetsDechByCollection?col="+$scope.currentCodeCollectionPorte)
		.success(function(data){
			
			console.log(_.pluck(data,"numeroCarnet"));
			
			$scope.listCrnDechForCurrCol =data;//_.pluck(data,"numeroCarnet");
			$scope.finderloader=false;
		});
		//$scope.chargerRetour();
	}
	$scope.dechargerOperation=function(){
		$scope.codeCollecteur=$scope.codeCollecteur1.idCollecteur;
		console.log($scope.codeCollecteur1);
		console.log("codeCollecteur="+$scope.codeCollecteur1.idCollecteur+"&carnetsDecharges="+_.pluck($scope.carnetsDechargeList,"nCarnet").toString()+"&carnetGroups="+_.pluck($scope.carnetsDechargeList,"nGroupe").toString());
	$http({
			
			method : 'POST',
			url    : $scope.operation.type,
			data: "codeCollecteur="+$scope.codeCollecteur1.idCollecteur+"&carnetsDecharges="+_.pluck($scope.carnetsDechargeList,"nCarnet").toString()+"&carnetGroups="+_.pluck($scope.carnetsDechargeList,"nGroupe").toString(),
			headers: {'Content-Type':'application/x-www-form-urlencoded'}
		})
	 	.success(function(data){
			$scope.chargerCollecteur();
			$scope.collectionPorte=data;
			console.log($scope.collectionPorte);
			$scope.chargerCollections();
			$scope.finderloader=false;
		})
		.error(function(data) {
			$scope.exception.message=data.message;
			console.log("error is "+data.message);
			$scope.finderloader=false;
		});
	}
	
	
	$scope.retourOperation=function(recettes){
		console.log("codeCollectionPorte="+$scope.currentCodeCollectionPorte+"&recettes="+recettes+"&nDebut="+_.pluck($scope.carnetsRetourList,"nDebut").toString()+"&carnetsRetour="+_.pluck($scope.carnetsRetourList,"numeroCarnet").toString()+"&carnetGroupes="+_.pluck($scope.carnetsRetourList,"groupe").toString());
		console.log("jj");
		console.log(_.pluck($scope.carnetsRetourList,"nCarnet").toString());
		$http({
				method : 'POST',
				url    : $scope.operation.type,
				data: "codeCollectionPorte="+$scope.currentCodeCollectionPorte+"&recettes="+recettes+"&nDebut="+_.pluck($scope.carnetsRetourList,"nDebut").toString()+"&carnetsRetour="+_.pluck($scope.carnetsRetourList,"numeroCarnet").toString()+"&carnetGroupes="+_.pluck($scope.carnetsRetourList,"groupe").toString(),
				headers: {'Content-Type':'application/x-www-form-urlencoded'}
			})
			.success(function(data){
				
				$scope.chargerCollecteur();
				$scope.collectionPorte=data;
				console.log($scope.collectionPorte);
				$scope.chargerCollections();
				$scope.finderloader=false;
			})
			.error(function(data) {
				$scope.exception.message=data.message;
				console.log("error is "+data.message);
				$scope.finderloader=false;
			});
		}
	$http.post("collectionsEncours?page="+$scope.pageCourante+"&size="+$scope.pageSize,$scope.filter)
	.success(function(data){
		$scope.pageCollectionsList=data;
		$scope.pages=new Array(data.totalPages);
		$scope.finderloader=false;
	})
	.error(function(data) {
		$scope.exception.message=data.message;
		console.log("error is "+data.message);
		$scope.finderloader=false;
	});
	$scope.chargerCollections=function(){
		$http.post("collectionsEncours?page="+$scope.pageCourante+"&size="+$scope.pageSize,$scope.filter)
		.success(function(data){
			$scope.pageCollectionsList=data;
			$scope.pages=new Array(data.totalPages);
			$scope.finderloader=false;
		});
	}
	
    
});



app.controller("coll_archiveesController",function($scope,_,$http,$sce,quiticeAprinter,$location,sharedLicence) {
	$scope.sharedLicence=sharedLicence.sharedLicence;
	$scope.collectionArchList=[];
	$scope.exception={message:null}
	$scope.pagesArch=[];
	$scope.pageCourante=0;
	$scope.pageSize=20;
	 $scope.filter={};
	
	$scope.content=null;
	$http.post("collectionsArchivees?page="+$scope.pageCourante+"&size="+$scope.pageSize,$scope.filter)
	.success(function(data){
		$scope.collectionArchList=data;
		$scope.pagesArch=new Array(data.totalPages);
	})
	
	.error(function(data) {
		$scope.exception.message=data.message;
		console.log("error is "+data.message);
	});
	
	$scope.filtrerArchivage=function(){
		console.log($scope.filter.journee);
		$http.post("collectionsArchivees?page="+$scope.pageCourante+"&size="+$scope.pageSize,$scope.filter)
		.success(function(data){
			$scope.collectionArchList=data;
			$scope.pagesArch=new Array(data.totalPages);
			
			console.log("collectionsArchivees?page="+$scope.pageCourante+"&size="+$scope.pageSize+"&journee="+$scope.filter.journee);
		})
		
		.error(function(data) {
			$scope.exception.message=data.message;
			console.log("error is "+data.message);
		});
	}
	var reportUrl = "http://localhost/api/report";
    var _print = function (parameter) {
        return $http.post(reportUrl, parameter, { responseType: 'arraybuffer' }).success(function (response) {
            return response;
        });
    };
    
	$scope.printQuitice=function(item){
		
		 quiticeAprinter.fillin(item);
		 console.log("ggg");
		 console.log(item);
		 $location.path('reportQuitice');
		 $scope.urldecharge = '#/reportQuitice';

	  
		
	}


	
	
});

app.controller("versementsController",function($scope,_,$http,$sce,sharedLicence) {
	$scope.sharedLicence=sharedLicence.sharedLicence;
	$scope.versementsList=[];
	$scope.exception={message:null}
	$scope.pagesVers=[];
	$scope.pageCourante=0;
	$scope.pageSize=20;
	$scope.montantDu=0;
	$scope.content=null;
	$scope.solde=0;
	$scope.nombrePVersements="";
	$scope.messag="";
	$scope.nombreVersements="";
	$http.get("getSolde")
	.success(function(data){
		$scope.solde=data;
		
		console.log(data);
	});
	$http.get("versements?page="+$scope.pageCourante+"&size="+$scope.pageSize)
	.success(function(data){
		$scope.versementsList=data;
		$scope.pagesVers=new Array(data.totalPages);
		$scope.nombrePVersements=data.totalPages;
		
		$scope.nombreVersements=data.totalVersements;
		console.log($scope.versementsList);
	})
	
	.error(function(data) {
		$scope.exception.message=data.message;
		console.log("error is "+data.message);
	});
	

	var reportUrl = "http://localhost/api/report";
    var _print = function (parameter) {
        return $http.post(reportUrl, parameter, { responseType: 'arraybuffer' }).success(function (response) {
            return response;
        });
    };
    
	$scope.printPaiement=function(item){
		
		var parameter={"template": { "shortid" : "V1kSXEmzW" },"data" : item  }	
		var reportUrl = "https://localhost/api/report";
		console.log(parameter);
		
	    $http.post(reportUrl, parameter, { responseType: 'arraybuffer' }).success(function (response) {
	    	   var file = new Blob([response], {type: 'application/pdf'});
	    	   
	           var fileURL = URL.createObjectURL(file);
	           console.log(fileURL);
	         //  $scope.content = $sce.trustAsResourceUrl(fileURL);
	        //   document.getElementById("jsrForm").submit();
	          window.open(fileURL);
	        });

	  
		
	}

	
	$scope.supprimerVersement=function(item){
    	console.log("kk");
    	console.log(item);
    	$scope.modeVersement="delete";
    	$scope.selectedVersement=item;
    	$http({
			method : 'POST',
			url    : 'removeVersement',
			data: "codeVersement="+item.idVersement,
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
		    }
		})
		.success(function(data) {
		if(data.errors) { 
		$scope.deletedVersement=item;
		$scope.errors=data;
		$scope.messag=data.cause;
		console.log(data.cause);
		$http.get("versements?page="+$scope.pageCourante+"&size="+$scope.pageSize)
			.success(function(data){
				$scope.versementsList=data;
				$scope.pagesVers=new Array(data.totalPages);
				$scope.nombrePVersements=data.totalPages;
	    		
				$scope.nombreVersements=data.totalVersements;
				console.log($scope.versementsList);
			});
		
	}

	})
	.error(function(data) {
		$scope.exception.message=data;
		console.log("error is "+data.message);
		$scope.messag="il est impossible de supprimer le collecteur";
	});
	}
	

	$scope.addVersement=function(versToAdd) {
				
		console.log("montant="+versToAdd.montantVersee+"&compte="+versToAdd.compteVersement+"&reference="+versToAdd.referenceVersement);
		
		$http({
			method : 'POST',
			url    : 'addVersement',
			data: "montant="+versToAdd.montantVersee+"&compte="+versToAdd.compteVersement+"&reference="+versToAdd.referenceVersement,
			headers: {'Content-Type':'application/x-www-form-urlencoded'}
		})
			.success(function(data){
			$scope.versementsList=[];
			$http.get("versements?page="+$scope.pageCourante+"&size="+$scope.pageSize)
			.success(function(data){
				$scope.versementsList=data;
				$scope.pagesVers=new Array(data.totalPages);
$scope.nombrePVersements=data.totalPages;
	    		
$scope.nombreVersements=data.totalVersements;
				console.log($scope.versementsList);
			})
			
			.error(function(data) {
				$scope.exception.message=data.message;
				console.log("error is "+data.message);
			});
		})
		.error(function(data) {
			$scope.exception.message=data.message;
			console.log("error is "+data.message);
		});
	
		
	    
	} 	
	
});

app.controller("indexController",function($scope,_,$http,$location,sharedLicence) {
	
	$scope.sharedLicence=sharedLicence.sharedLicence;
	$scope.solde=null;
	$scope.password=null;
	$scope.ticketSearchedFor=null;
	$scope.localSearchedFor=null;
	$scope.aneesList=[{"designation":2016}];
	$scope.moisList=[{"id":1,"designation":"Janvier"},{"id":2,"designation":"Fevrier"},{"id":3,"designation":"Mars"}];
	$scope.exception={message:null};
	$scope.menu=[{libele:"Indicateurs",path:"#/",icon:"fa fa-bars fa-3x"},{libele:"Collectes encours",path:"#/par_encours",icon:"fa-folder-open"},{libele:"Collectes archivées",path:"#/coll_archivees",icon:"fa-archive"},{libele:"Saisies des imputations",path:"#/remplissage",icon:"fa-refresh"},{libele:"Gestion des droits",path:"#/security",icon:"fa-users"},{libele:"Saisie des carnets",path:"#/carnets",icon:"fa-book"},{libele:"saisie des collecteurs",path:"#/collecteurs",icon:"fa-user"},{libele:"Saisie des locaux",path:"#/locaux",icon:"fa-building-o"},{libele:"Paiements",path:"#/versements",icon:"fa-money"},{libele:"Imports",path:"#/import",icon:"fa-money"}];
	$scope.selectedMenu=null;
	$scope.currentLogedUser=null;
	$scope.currentRoles=null;
	$scope.validationStatus=null;
	$scope.exp=null;
	$scope.formater=function(){
		console.log($scope.password);
	if($scope.password=="1") {
		
		$http.get("formater")
		.success(function(data){
			
			$scope.mess="le system est formaté";
		
		})
		
		.error(function(data) {
			$scope.exception.message=data.message;
			
			console.log("error is "+data.message);
		});
	
	}
	else $scope.mess="le mot de passe est erronée";
	$location.path('resultFormat');
	}
	$http.get("getSolde")
	.success(function(data){
		$scope.solde=data;
		
		console.log(data);
	});
	$scope.locateTicketHistry=function() {
		
			$scope.localSearchedFor=null;
		console.log($scope.ticketSearchedFor);
		
		$http.get("locateTicketHistry?numTicket="+$scope.ticketSearchedFor)
		.success(function(data){
			$scope.ticketSearchedForD=data;
			console.log("jjj");
			console.log($scope.ticketSearchedForD);
			$scope.localSearchedForD=null;
		
		})
		
		.error(function(data) {
			$scope.exception.message=data.message;
			
			console.log("error is "+data.message);
		});
		$location.path('resultSearch');
	
	} 
	

	$scope.locateLocalHistry=function() {
		console.log($scope.localSearchedFor);
		
		$http.get("locateLocalHistry?numLocal="+$scope.localSearchedFor)
		.success(function(data){
			$scope.localSearchedForD=data;
			console.log("jjj");
			console.log($scope.localSearchedForD);
			$scope.ticketSearchedForD=null;
		
		})
		
		.error(function(data) {
			$scope.exception.message=data.message;
			
			console.log("error is "+data.message);
		});
		$location.path('resultSearch');
	

}
	$scope.select=function(m) {
		$scope.selectedMenu=m;
	}
	
	
	$http.get("getLogedUser")
	.success(function(data){
	
		$scope.currentLogedUser=data.username;
		$scope.validationStatus=data.validationStatus;
		$scope.exp=data.exp;
		$scope.currentRoles=data.roles;
		sharedLicence.fillin(data.Lic);
		$scope.sharedLicence=sharedLicence.sharedLicence;
		console.log($scope.currentRoles);
		console.log("validationStatus:");
		console.log($scope.validationStatus);
		console.log("SharedLicence");
		console.log($scope.sharedLicence);
	})
	
	.error(function(data) {
		$scope.exception.message=data.message;
		console.log("error is "+data.message);
	});
});


app.controller("securityController",function($scope,_,$http,sharedLicence) {
	$scope.sharedLicence=sharedLicence.sharedLicence;
	$scope.exception={message:null};
	$scope.usersList=[];
	$scope.user={};
	$scope.ujj="";
	$scope.selectedUser={};
	$selectedUser1={};
	$scope.mode={value:"form"};
	$scope.listRoles=['ADMIN','CAISSIER','SAISIE','ZF'];
	$selectedUser={
		    listRoles: []
	  };
	 
	console.log($scope.selectedUserroles);
	
	$scope.accorderRolesToUser=function(u) {
		$scope.ujj=$scope.selectedUser.listRoles.toString();
		
		console.log("u="+u.username+"&rolesToAdd="+$scope.ujj);
		
		
		

	$http({
		method : 'POST',
		url    : 'addRolesToUser',
		data: "u="+u.username+"&rolesToAdd="+$scope.ujj,
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
	    }
	})
	.success(function(data) {
		
			$http.get("findUsers")
			.success(function(data){
			
				$scope.usersList=data;
				
			});
			
		})
	
		.error(function(data) {
			$scope.exception.message=data.message;
			console.log("error is "+data.message);
		});
		
	}
	
	
	$scope.loadRoles=function(u){
		$scope.selectedUser=u;
		$scope.selectedRoles=_.pluck(u.roles,"role");
		console.log("jjhj");
		console.log($scope.selectedRoles);
	}
	$scope.removeUser=function(item) {
	
	
		$http({
		method : 'POST',
		url    : 'removeUser',
		data: "u="+item,
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
	    }
	})
		.success(function(data) {
			 
			$scope.user=item;
			$scope.successme="suprimé avec succes";
			
			$http.get("findUsers")
			.success(function(data){
				$scope.usersList=data;
			});
			
		
		})
		.error(function(data) {
		$scope.exception.message=data.message;
		console.log("error is "+data.message);
	});
		
	}
	
	$scope.saveUser=function(){
		$http.post("addUser",$scope.user)
		.success(function(data) {
			if(!data.errors) { 
			$scope.user=data;
			$scope.errors=null;
			$scope.mode.value="confirm";
			$http.get("findUsers")
			.success(function(data){
			
				$scope.usersList=data;
				
			});
			
		}
		else
			{
			$scope.errors=data;
			}
		})
		.error(function(data) {
		$scope.exception.message=data.message;
		console.log("error is "+data.message);
	});
	};
	
	
	$http.get("findUsers")
	.success(function(data){
	
		$scope.usersList=data;
		
	})
	
	.error(function(data) {
		$scope.exception.message=data.message;
		console.log("error is "+data.message);
	});	
	
});





app.controller("tableauController",function($scope,_,$http,sharedLicence) {
	$scope.sharedLicence='LICENSE_VALID';
	$scope.quartierList=[];
	$scope.solde="";
	$scope.finderloader=true;
	
	 $scope.isLoading = function() {
	        return $http.pendingRequests.length > 0;
	    };
	
	$http.get("getSolde")
	.success(function(data){
		$scope.solde=data;
		$scope.finderloader=false;
		console.log(data);
	});
	$scope.req = $http.get("tableauRecouv")
	.success(function(data){
		console.log("qrt");
		console.log(data);
		$scope.quartierList=data;
		$scope.finderloader=false;
	});
	
	 $scope.downloadQuartier= function(codeQuartier) {
		
		
	  };
	
});

app.controller("remplissageController",function($scope,_,$http,sharedLicence) {
	$scope.sharedLicence=sharedLicence.sharedLicence;
	$scope.exception={message:null};
	console.log("ggg");
	$scope.pageSize=20;
	$scope.pageCourante=0;
	$scope.filter={};
	$scope.pageCollectionsARempList=[];
	 
	$scope.currentCodeCollectionPorte=-1;
	$scope.locaux=[];
	$scope.rempList=[];
	 $scope.rempToAdd = [{
		    nTicket: [],
		    nLocal: []
		  }];
	 $scope.arrLoc=[];
	 $scope.addremp= function(itemToAdd) {
		
		    var index = $scope.rempToAdd.indexOf(itemToAdd);

		    $scope.rempToAdd.splice(index, 1);

		    $scope.rempList.push(angular.copy(itemToAdd));
		  
		  
	
		  };
		  
		  
	$scope.saveImputations=function(rempToAdd1) {
		$scope.tickets=[];
		$scope.locaux=[];
		console.log("save action");

		tickets=_.pluck(rempToAdd1,"numero").toString();
		
		angular.forEach(rempToAdd1, function(value, index) {
	    	if(value.local!=null)	$scope.val=value.local.codeImputation;
	    	else          $scope.val=-1;
	    	$scope.locaux.push($scope.val);
		        console.log($scope.val);
		        
		})
		      
		
		console.log("codeCollectionPorte="+$scope.currentCodeCollectionPorte+"&numTickets="+tickets+"&codeImputations="+$scope.locaux);
		
		$http({
			method : 'POST',
			url    : 'remplir',
			data: "codeCollectionPorte="+$scope.currentCodeCollectionPorte+"&numTickets="+tickets+"&codeImputations="+$scope.locaux,
			headers: {'Content-Type':'application/x-www-form-urlencoded'}
		})
		.error(function(data) {
			$scope.exception.message=data.message;
			console.log("error is "+data.message);
		});
		
	
	} 

	$scope.goToRemplissage=function(idcollection){
		$scope.currentCodeCollectionPorte=idcollection;
		
		 $http.get("remplir?codeCollectionPorte="+$scope.currentCodeCollectionPorte)
			.success(function(data){
				console.log(data);
				$scope.imputations=data;
				$scope.rempToAdd={nTicket:data.tickets,nLocal:data.local};
				console.log("ticket retournee :");
     			console.log($scope.rempToAdd);
				})
				.error(function(data) {
		$scope.exception.message=data.message;
		console.log("error is "+data.message);
	});  
		 //	$scope.imputationsLocals=_.pluck($scope.imputations,"nDebut").toString();
		 // $scope.imputationTickets=_.pluck($scope.imputations,"nDebut").toString();
		//  $scope.chargerRetour();
	}

		    
		    $http.post("collectionsArchivees?page="+$scope.pageCourante+"&size="+$scope.pageSize,$scope.filter)
			.success(function(data){
				console.log(data);
				$scope.pageCollectionsARempList=data;
				$scope.pagesARemp =new Array(data.totalPages);
				
			})   
		    
			.error(function(data) {
				$scope.exception.message=data.message;
				console.log("error is "+data.message);
			});	
		    
			$scope.filtrerArchivage=function(){
				console.log($scope.filter.journee);
				$http.post("collectionsArchivees?page="+$scope.pageCourante+"&size="+$scope.pageSize,$scope.filter)
				.success(function(data){
					$scope.pageCollectionsARempList=data;
					$scope.pagesARemp =new Array(data.totalPages);
					
					console.log("collectionsArchivees?page="+$scope.pageCourante+"&size="+$scope.pageSize+"&journee="+$scope.filter.journee);
				})
				
				.error(function(data) {
					$scope.exception.message=data.message;
					console.log("error is "+data.message);
				});
			}
	
});




app.controller("carnetsController",function($scope,_,$http,sharedLicence) {
	$scope.sharedLicence=sharedLicence.sharedLicence;
	$scope.carnet={};
	$scope.addedCarnet=null;
	$scope.pagesCarnets=[];
	$scope.exception={message:null}
	$scope.messag="";
	$scope.pageCourante=0;
	 $scope.pageSize=20;
	console.log("carnets?page="+$scope.pageCourante+"&size="+$scope.pageSize);
	
	$http.get("carnets?page="+$scope.pageCourante+"&size="+$scope.pageSize)
	.success(function(data){
	
		$scope.carnetsList=data.carnets;
		$scope.pages=new Array(data.totalPages);
		console.log(data);
		
	});
	
	$scope.supprimerCarnet=function(cr){
		$http({
			method : 'POST',
			url    : 'removeCarnet',
			data: "ngroup="+cr.groupe+"&ncarnet="+ cr.numeroCarnet,
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
		    }
		})
		.success(function(data) {
		if(data.errors) { 
		$scope.deletedCarnet=cr;
		$scope.errors=data;
		$scope.messag=data.cause;
		console.log(data.cause);
		$http.get("carnets?page="+$scope.pageCourante+"&size="+$scope.pageSize)
		.success(function(data){
		
			$scope.carnetsList=data.carnets;
			$scope.pages=new Array(data.totalPages);
			console.log(data);
		});
		
	}

	})
	.error(function(data) {
		$scope.exception.message=data;
		console.log("error is "+data.message);
		$scope.messag="il est impossible de supprimer le carnet";
	});
	}
	
	$scope.ajouterCarnet=function() {
		
		$http.post("carnets",$scope.carnet)
			.success(function(data) {
			if(!data.errors) { 
			$scope.addedCarnet=data;
			$scope.errors=null;
			
			$http.get("carnets?page="+$scope.pageCourante+"&size="+$scope.pageSize)
			.success(function(data){
			
				$scope.carnetsList=data.carnets;
				$scope.pages=new Array(data.totalPages);
				console.log(data);
			});
			
		}
		else
			{
			$scope.errors=data;
			}
		})
		.error(function(data) {
			$scope.exception.message=data.message;
			console.log("error is "+data.message);
		});
	}
	

});



app.controller("collecteursController",function($scope,_,$http,sharedLicence) {
	$scope.sharedLicence=sharedLicence.sharedLicence;
	$scope.messag="";
	$scope.modeCollecteur="list";
	$scope.collecteurs={};
	$scope.addedCollecteur=null;
	$scope.pagesCollecteur=[];
	$scope.exception={message:null}
	$scope.addedCol="";
	$scope.pageCourante=0;
	 $scope.pageSize=20;
	 $scope.changedCol="";
	console.log("carnets?page="+$scope.pageCourante+"&size="+$scope.pageSize);
	
	$http.get("collecteurs?page="+$scope.pageCourante+"&size="+$scope.pageSize)
	.success(function(data){
		$scope.collecteurs=data.collecteurs;
		$scope.pages=new Array(data.totalPages);
		console.log(data);
		
	});
	
	$scope.ajouterCollecteur=function() {
		
		$http.post("collecteurs",$scope.addedCollecteur)
			.success(function(data) {
				console.log($scope.addedCollecteur);
			if(!data.errors) { 
			$scope.addedCol="ok";
			$scope.errors=null;
			
			$http.get("collecteurs?page="+$scope.pageCourante+"&size="+$scope.pageSize)
			.success(function(data){
				$scope.collecteurs=data.collecteurs;
				$scope.pages=new Array(data.totalPages);
				console.log(data);
			});
			
		}
		else
			{
			$scope.errors=data;
			}
		})
		.error(function(data) {
			$scope.exception.message=data.message;
			console.log("error is "+data.message);
		});
	}
	
	$scope.modifierCollecteur=function(item){
		$scope.modeCollecteur="change";
		$scope.selectedCollecteur=item;
		$scope.selectedCollecteur.newnom=item.nom;
		$scope.selectedCollecteur.newtel=item.tel;
		$scope.selectedCollecteur.newcode=item.code;
		console.log($scope.modeCollecteur);
	}
	$scope.enregistrerCollecteur=function(item){
		$scope.modeCollecteur="list";
		item.nom=$scope.selectedCollecteur.newnom;
		item.tel=$scope.selectedCollecteur.newtel;
		item.code=$scope.selectedCollecteur.newcode;
		
		$http.post("collecteurs",item)
		.success(function(data) {
			console.log(item);
		if(!data.errors) { 
		$scope.changedCol="ok";
		$scope.errors=null;
		
		$http.get("collecteurs?page="+$scope.pageCourante+"&size="+$scope.pageSize)
		.success(function(data){
			$scope.collecteurs=data.collecteurs;
			$scope.pages=new Array(data.totalPages);
			console.log(data);
		});
		
	}
	else
		{
		$scope.errors=data;
		}
	})
	.error(function(data) {
		$scope.exception.message=data.message;
		console.log("error is "+data.message);
	});
		console.log(item);
	}
    $scope.supprimerCollecteur=function(item){
    	console.log("kk");
    	console.log(item.idCollecteur);
    	$scope.modeCollecteur="delete";
    	$scope.selectedCollecteur=item;
    	$http({
			method : 'POST',
			url    : 'removeCollecteur',
			data: "codeCollecteur="+item.idCollecteur,
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
		    }
		})
		.success(function(data) {
		if(data.errors) { 
		$scope.deletedCollecteur=item;
		$scope.errors=data;
		$scope.messag=data.cause;
		console.log(data.cause);
		$http.get("collecteurs?page="+$scope.pageCourante+"&size="+$scope.pageSize)
		.success(function(data){
			$scope.collecteurs=data.collecteurs;
			$scope.pages=new Array(data.totalPages);
			console.log(data);
		});
		
	}

	})
	.error(function(data) {
		$scope.exception.message=data;
		console.log("error is "+data.message);
		$scope.messag="il est impossible de supprimer le collecteur";
	});
	}
   
});



app.controller("locauxController",function($scope,_,$http,sharedLicence) {
	$scope.sharedLicence=sharedLicence.sharedLicence;
	$scope.changedLoc=null;
	$scope.modeLocal="list";
	$scope.successm="";
	$scope.modesearch='ordinaire';
	$scope.nombreLocals="";
	$scope.nombrePLocals="";
	$scope.nouveauLoc={};
	$scope.addedLocal=null;
	$scope.selectedLocal=null;
	$scope.codeSearche='';
	$scope.groupeSearche='';
	$scope.quartierSearche='';
	$scope.typeSearche='';
	$scope.resultatEnqueteSearche='';
	$scope.quartierSearche='';
	$scope.codeAddress='';
	$scope.pageSize=20;
	
	/* bein autocomplete*/
	 var _selected;
	 $scope.selected = undefined;
	 
	  $scope.getSugg = function(val) {
		    return $http.get('autocomplocals', {
		      params: {
		    	searchcode : val
		        
		      }
		    }).then(function(response){
		    	console.log("response");
		    	console.log(response);
		      return response.data.content.map(function(item){
		        return item.codeImputation;
		      });
		    });
		  };
		  
		  
		  
		  $scope.getSuggAddresses = function(val) {
			    return $http.get('autocompAddresses', {
			      params: {
			    	searchaddress : val
			      }
			    }).then(function(response){
			    	console.log("response");
			    	console.log(response);
			      return response.data.content.map(function(item){
			        return item.address;
			      });
			    });
			  };
			  
    $scope.ngModelOptionsSelected = function(value) {
			    if (arguments.length) {
			      _selected = value;
			    } else {
			      return _selected;
			    }
			  };
		  
	  $scope.modelOptions = {
				     debounce: {
				      default: 500,
				      blur: 250
				    },
				    getterSetter: true
				  };	 var _selected;
					 $scope.selected = undefined;
					 
					  $scope.getSugg = function(val) {
						    return $http.get('autocomplocals', {
						      params: {
						    	searchcode : val
						        
						      }
						    }).then(function(response){
						    	console.log("response");
						    	console.log(response);
						      return response.data.content.map(function(item){
						        return item.codeImputation;
						      });
						    });
						  };
				    $scope.ngModelOptionsSelected = function(value) {
							    if (arguments.length) {
							      _selected = value;
							    } else {
							      return _selected;
							    }
							  };
						  
					  $scope.modelOptions = {
								     debounce: {
								      default: 500,
								      blur: 250
								    },
								    getterSetter: true
								  };
		  /* end autocomplete*/

	
	
	
	$scope.order = {
			codeImputation : true,
			zone : true
	};
	
	$scope.onSort=function(col){
		$scope.sort=col;
		$scope.order[col]=!$scope.order[col];
		
    
    	$scope.codeAddress='-1'
    	$scope.codeSearche=-1;
    	$scope.groupeSearche.code
    	$scope.quartierSearche={code:-1};
    	$scope.typeSearche={code:-1};
    	$scope.resultatEnqueteSearche='Tous';
    	if($scope.codeAddress=='') $scope.codeAddress='-1';
    	if($scope.codeSearche=='' ) $scope.codeSearche=-1;
    	if($scope.isUndefined($scope.groupeSearche.code)) $scope.groupeSearche={code:-1};
    	if($scope.isUndefined($scope.quartierSearche.code)) $scope.quartierSearche={code:-1};
    	if($scope.typeSearche=='') $scope.typeSearche={code:-1};
    	if($scope.resultatEnqueteSearche='undefined') $scope.resultatEnqueteSearche='Tous';
		
		
		$http.get("locals?page="+$scope.pageCourante+"&size="+$scope.pageSize+"&orderColumn="+$scope.sort+"&typeOrdering="+$scope.order['codeImputation']+
    			"&searchCode="+$scope.codeSearche+"&groupeSearche="+$scope.groupeSearche.code+"&quartierSearche="+$scope.quartierSearche.code+"&typeSearche="+$scope.typeSearche.code+"&codeAddress="+ $scope.codeAddress+"&enqtype="+$scope.resultatEnqueteSearche)
		.success(function(data){
		
			$scope.locaux=data.locals;
			$scope.pages=new Array(data.totalPages);
			$scope.nombrePLocals=data.totalPages;
    		
    		$scope.nombreLocals=data.totalLocals;
			console.log(data);
			
		});
	}
	
	$scope.goToPage=function(p){
		
		if($scope.modesearch=='ordinaire') $scope.listeComplete(p);
		if($scope.modesearch=='filtrage') $scope.reafficher(p);
	}
    $scope.listeComplete=function(p0){
    	$scope.pageCourante=p0;
    	$scope.codeAddress='-1'
    	$scope.codeSearche=-1;
    	$scope.groupeSearche.code
    	$scope.quartierSearche={code:-1};
    	$scope.typeSearche={code:-1};
    	$scope.resultatEnqueteSearche='Tous';
    	if($scope.codeAddress=='') $scope.codeAddress='-1';
    	if($scope.codeSearche=='' ) $scope.codeSearche=-1;
    	if($scope.isUndefined($scope.groupeSearche.code)) $scope.groupeSearche={code:-1};
    	if($scope.isUndefined($scope.quartierSearche.code)) $scope.quartierSearche={code:-1};
    	if($scope.typeSearche=='') $scope.typeSearche={code:-1};
    	if($scope.resultatEnqueteSearche='undefined') $scope.resultatEnqueteSearche='Tous';
    	console.log("ttt");
    	console.log("locals?page="+p0+"&size="+$scope.pageSize+"&orderColumn="+$scope.sort+"&typeOrdering="+$scope.order['codeImputation']+
    			"&searchCode="+$scope.codeSearche+"&groupeSearche="+$scope.groupeSearche.code+"&quartierSearche="+$scope.quartierSearche.code+"&typeSearche="+$scope.typeSearche.code+"&codeAddress="+ $scope.codeAddress);
    	
    	
    	$http.get("locals?page="+$scope.pageCourante+"&size="+$scope.pageSize+"&orderColumn="+$scope.sort+"&typeOrdering="+$scope.order['codeImputation']+
    			"&searchCode="+$scope.codeSearche+"&groupeSearche="+$scope.groupeSearche.code+"&quartierSearche="+$scope.quartierSearche.code+"&typeSearche="+$scope.typeSearche.code+"&codeAddress="+ $scope.codeAddress+"&enqtype="+$scope.resultatEnqueteSearche)
    	.success(function(data){
    	
    		$scope.locaux=data.locals;
    		$scope.pages=new Array(data.totalPages);
    		$scope.nombreLocals=data.nombreLocals;
    		$scope.nombrePLocals=data.totalPages;
   		$scope.nombreLocals=data.totalLocals;
    		console.log(data);
    	
    	});
    	$scope.modesearch='ordinaire'	
};
	
	    $scope.reafficher=function(p0){
	    	console.log("Code search");
	    	console.log($scope.codeSearche);
	    	$scope.pageCourante=p0;
	    	if($scope.codeAddress=='') $scope.codeAddress='-1'
	    	if($scope.codeSearche=='' ) $scope.codeSearche=-1;
	    	if($scope.isUndefined($scope.groupeSearche.code)) $scope.groupeSearche={code:-1};
	    	if($scope.isUndefined($scope.quartierSearche.code)) $scope.quartierSearche={code:-1};
	    	if($scope.typeSearche=='') $scope.typeSearche={code:-1};
	    	
	    	console.log("ttt");
	    	console.log("locals?page="+$scope.pageCourante+"&size="+$scope.pageSize+"&orderColumn="+$scope.sort+"&typeOrdering="+$scope.order['codeImputation']+
	    			"&searchCode="+$scope.codeSearche+"&groupeSearche="+$scope.groupeSearche.code+"&quartierSearche="+$scope.quartierSearche.code+"&typeSearche="+$scope.typeSearche.code+"&codeAddress="+ $scope.codeAddress);
	    	
	    	
	    	$http.get("locals?page="+p0+"&size="+$scope.pageSize+"&orderColumn="+$scope.sort+"&typeOrdering="+$scope.order['codeImputation']+
	    			"&searchCode="+$scope.codeSearche+"&groupeSearche="+$scope.groupeSearche.code+"&quartierSearche="+$scope.quartierSearche.code+"&typeSearche="+$scope.typeSearche.code+"&codeAddress="+ $scope.codeAddress+"&enqtype="+$scope.resultatEnqueteSearche)
	    	.success(function(data){
	    	
	    		$scope.locaux=data.locals;
	    		$scope.pages=new Array(data.totalPages);
	    		$scope.nombrePLocals=data.totalPages;
	    		
	    		$scope.nombreLocals=data.totalLocals
	    		
	    	});
	    	$scope.modesearch='filtrage'
	    	
	};
	$scope.arrow=function(col){
		
		return $scope.order[col]? ">":"<";
	}
	
	$scope.pagesLocal=[];
	$scope.exception={message:null};
	$scope.zoneList=[];
	$scope.quartierList=[];
	$scope.groupeList=[];
	$scope.typesListe=[];
	$scope.resultatEnqueteList= ['Tous','Complet','Incomplet','Refus'];
	$scope.messag="";
	
	$scope.pageCourante=0;
	$scope.pageSize=20;
	console.log("locals?page="+$scope.pageCourante+"&size="+$scope.pageSize);
	
	
	
	$http.get("zones")
	.success(function(data){
		$scope.zoneList=data;
		console.log(data);
	});
	
	$http.get("quartiers")
	.success(function(data){
		$scope.quartierList=data;
		console.log("Quq");
		console.log(data);
	});
	
	
	$http.get("types")
	.success(function(data){
		$scope.typesList=data;
		console.log("types:");
		console.log(data);
	});
	
	$http.get("groupes")
	.success(function(data){
		$scope.groupeList=data;
		
		console.log(data);
	});  
	
	$http.get("categories")
	.success(function(data){
		$scope.categorieList=data;
		
		console.log(data);
	}); 
	$scope.isUndefined = function (thing) {
	    return (typeof thing === "undefined");
	}
	$scope.sort='codeImputation';
	$scope.order['codeImputation']=true;
	if($scope.codeAddress=='') $scope.codeAddress='-1'
	
    if($scope.codeSearche=='') $scope.codeSearche=-1;
	if($scope.isUndefined($scope.groupeSearche.code)) $scope.groupeSearche={code:-1};
	if($scope.isUndefined($scope.quartierSearche.code)) $scope.quartierSearche={code:-1};
	if($scope.typeSearche=='') $scope.typeSearche={code:-1};
	$scope.resultatEnqueteSearche='Tous';
	console.log("locals?page="+$scope.pageCourante+"&size="+$scope.pageSize+"&orderColumn="+$scope.sort+"&typeOrdering="+$scope.order['codeImputation']+
			"&searchCode="+$scope.codeSearche+"&groupeSearche="+$scope.groupeSearche.code+"&quartierSearche="+$scope.quartierSearche.code+"&typeSearche="+$scope.typeSearche.code+"&codeAddress="+ $scope.codeAddress);
	console.log($scope.isUndefined($scope.groupeSearche.code));
	
	$http.get("locals?page="+$scope.pageCourante+"&size="+$scope.pageSize+"&orderColumn="+$scope.sort+"&typeOrdering="+$scope.order['codeImputation']+
			"&searchCode="+$scope.codeSearche+"&groupeSearche="+$scope.groupeSearche.code+"&quartierSearche="+$scope.quartierSearche.code+"&typeSearche="+$scope.typeSearche.code+"&codeAddress="+ $scope.codeAddress+"&enqtype="+$scope.resultatEnqueteSearche)
	.success(function(data){
	
		$scope.locaux=data.locals;
		$scope.pages=new Array(data.totalPages);
		$scope.nombrePLocals=data.totalPages;
		
		$scope.nombreLocals=data.totalLocals;
		console.log(data);
		
	});
	
	$scope.supprimerLocal=function(item){
		$scope.selectedLocal={};
    	console.log("kk");
    	console.log(item.codeImputation);
    	$scope.modeLocal="delete";
    	$scope.selectedLocal=item;
    	$http({
			method : 'POST',
			url    : 'removeLocal',
			data: "codeLocal="+item.codeImputation,
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
		    }
		})
		.success(function(data) {
		if(data.errors) { 
		$scope.deletedLocal=item;
		$scope.errors=data;
		$scope.messag=data.cause;
		console.log(data.cause);
		$http.get("locals?page=0"+"&size="+$scope.pageSize+"&orderColumn="+$scope.sort+"&typeOrdering="+$scope.order['codeImputation']+
    			"&searchCode="+$scope.codeSearche+"&groupeSearche="+$scope.groupeSearche.code+"&quartierSearche="+$scope.quartierSearche.code+"&typeSearche="+$scope.typeSearche.code+"&codeAddress="+ $scope.codeAddress+"&enqtype="+$scope.resultatEnqueteSearche)
    	.success(function(data){
    	
    		$scope.locaux=data.locals;
    		$scope.pages=new Array(data.totalPages);
    		$scope.nombrePLocals=data.totalPages;
    		
    		$scope.nombreLocals=data.totalLocals;
    		console.log('jjj'+data.totalPages);
    		
    	});
		
	}

	})
	.error(function(data) {
		$scope.exception.message=data;
		console.log("error is "+data.message);
		$scope.messag="il est impossible de supprimer le collecteur";
	});
	}
	$scope.modifierLocal=function(item){
		$scope.modeLocal="change";
		$scope.selectedLocal=item;
		console.log($scope.modeLocal);
	}
	
	$scope.enregistrerLocal=function(item){
		$scope.modeLocal="list";
		
		console.log("hhh");
		console.log($scope.selectedLocal);
		$http.post("locals",$scope.selectedLocal)
		.success(function(data) {
			console.log("hhh");
			console.log(item);
		if(!data.errors) { 
		$scope.changedLoc="ok";
		$scope.errors=null;
		
		
	}
	else
		{
		$scope.errors=data;
		}
	})
	.error(function(data) {
		$scope.exception.message=data.message;
		console.log("error is "+data.message);
	});
		console.log(item);
	}
		
		$scope.saveLocal=function(){
			$http.post("locals",$scope.nouveauLoc)
			.success(function(data) {
				if(!data.errors) { 
					$scope.nouveauLoc=data;
					$scope.errors=null;
					$scope.listeComplete(0);
					$scope.successm="ajouté avec success";
				}
				else
					{
					$scope.errors=data;
					}
			})
			.error(function(data) {
			$scope.exception.message=data.message;
			console.log("error is "+data.message);
		});
	}

	
});
app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
app.controller("UploadCtrl",function($scope,_,$http,sharedLicence) {
	$scope.sharedLicence=sharedLicence.sharedLicence;
	$scope.quartierFile=null;
	$scope.zoneFile=null;
	$scope.activitiesFile=null;
	$scope.categoriesFile=null;
	$scope.groupesFile=null;
	$scope.localsFile=null;
	
	$scope.importerQuartier=function() {
		var file = $scope.quartierFile;
		 console.log('file is ' );
		console.dir(file);
	
		var fd = new FormData();
		fd.append('fileUpload', file);
		/*	fd.append('user',angular.toJson($scope.user,true));
		console.log('Socpe of user'+$scope.user);*/
		$http.post("importerQuartiers", fd, {
		transformRequest : angular.identity,
		headers : {
		'Content-Type' : undefined
		}
		}).success(function() {
		console.log('success');
		}).error(function() {
		console.log('error');
		});
	}
	$scope.importerZones=function() {
		var file = $scope.zoneFile;
		 console.log('file is ' );
		console.dir(file);
	
		var fd = new FormData();
		fd.append('fileUpload', file);
		/*	fd.append('user',angular.toJson($scope.user,true));
		console.log('Socpe of user'+$scope.user);*/
		$http.post("importerZones", fd, {
		transformRequest : angular.identity,
		headers : {
		'Content-Type' : undefined
		}
		}).success(function() {
		console.log('success');
		}).error(function() {
		console.log('error');
		});
	
	}
	$scope.importerActivities=function(){
		var file = $scope.activitiesFile;
		 console.log('file is ' );
		console.dir(file);
	
		var fd = new FormData();
		fd.append('fileUpload', file);
		/*	fd.append('user',angular.toJson($scope.user,true));
		console.log('Socpe of user'+$scope.user);*/
		$http.post("importerActivites", fd, {
		transformRequest : angular.identity,
		headers : {
		'Content-Type' : undefined
		}
		}).success(function() {
		console.log('success');
		}).error(function() {
		console.log('error');
		});
	
	}
	$scope.importerCategories=function(){
		var file = $scope.categoriesFile;
		 console.log('file is ' );
		console.dir(file);
	
		var fd = new FormData();
		fd.append('fileUpload', file);
		/*	fd.append('user',angular.toJson($scope.user,true));
		console.log('Socpe of user'+$scope.user);*/
		$http.post("importerCategories", fd, {
		transformRequest : angular.identity,
		headers : {
		'Content-Type' : undefined
		}
		}).success(function() {
		console.log('success');
		}).error(function() {
		console.log('error');
		});
	
	}
	$scope.importerGroupes=function(){
		var file = $scope.groupesFile;
		 console.log('file is ' );
		console.dir(file);
	
		var fd = new FormData();
		fd.append('fileUpload', file);
		/*	fd.append('user',angular.toJson($scope.user,true));
		console.log('Socpe of user'+$scope.user);*/
		$http.post("importerGroupes", fd, {
		transformRequest : angular.identity,
		headers : {
		'Content-Type' : undefined
		}
		}).success(function() {
		console.log('success');
		}).error(function() {
		console.log('error');
		});
	
		
	}
	$scope.importerLocals=function(){
		var file = $scope.localsFile;
		 console.log('file is ' );
		console.dir(file);
	
		var fd = new FormData();
		fd.append('fileUpload', file);
		/*	fd.append('user',angular.toJson($scope.user,true));
		console.log('Socpe of user'+$scope.user);*/
		$http.post("importerLocals", fd, {
		transformRequest : angular.identity,
		headers : {
		'Content-Type' : undefined
		}
		}).success(function() {
		console.log('success');
		}).error(function() {
		console.log('error');
		});
	
	
	}
   $scope.importerCarnets=function(){
	   var file = $scope.carnetsFile;
		 console.log('file is ' );
		console.dir(file);
	
		var fd = new FormData();
		fd.append('fileUpload', file);
		/*	fd.append('user',angular.toJson($scope.user,true));
		console.log('Socpe of user'+$scope.user);*/
		$http.post("importerCarnets", fd, {
		transformRequest : angular.identity,
		headers : {
		'Content-Type' : undefined
		}
		}).success(function() {
		console.log('success');
		}).error(function() {
		console.log('error');
		});
	
	
	}

});



app.config(['$routeProvider', function ($routeProvider,ngProgress) {
    $routeProvider.when('/par_encours', {
      templateUrl: 'home/par_encours.html',
      controller: 'myzfController'
    
    });
    $routeProvider.when('/coll_archivees', {
        templateUrl: 'home/coll_archivees.html',
        controller: 'coll_archiveesController'
     });
    
    $routeProvider.when('/carnets', {
        templateUrl: 'home/carnets.html',
        controller: 'carnetsController'
     });
    
    $routeProvider.when('/collecteurs', {
        templateUrl: 'home/collecteurs.html',
        controller: 'collecteursController'
     });
    $routeProvider.when('/locaux', {
        templateUrl: 'home/locaux.html',
        controller: 'locauxController'
     });
    
    $routeProvider.when('/versements', {
        templateUrl: 'home/versements.html',
        controller: 'versementsController'
     });
    $routeProvider.when('/remplissage', {
        templateUrl: 'home/remplissage.html',
        controller: 'remplissageController'
      });
    $routeProvider.when('/', {
        templateUrl: 'home/tdb.html',
        controller: 'tableauController'
      });
  
      $routeProvider.when('/reportDecharge', {
          templateUrl: 'home/reportDecharge.html',
          controller: 'reportDechargeController'
        });
        $routeProvider.when('/reportQuitice', {
            templateUrl: 'home/reportQuitice.html',
            controller: 'reportQuiticeController'
          });
        $routeProvider.when('/import', {
            templateUrl: 'home/importer.html',
            controller: 'UploadCtrl'
          });
          $routeProvider.when('/resultSearch', {
              templateUrl: 'home/resultSearch.html',
              controller: 'indexController'
            });
          $routeProvider.when('/resultFormat', {
              templateUrl: 'home/resultFormat.html',
              controller: 'indexController'
            });
    $routeProvider.when('/security', {
        templateUrl: 'home/security.html',
        controller: 'securityController'
      
      });
    
  }]);