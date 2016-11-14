
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


	var app=angular.module("mygcmApp",['underscore',"checklist-model",'ngRoute','ui.bootstrap']);
	


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


app.controller("searchpageController",function($scope,_,$http) {

    $scope.debut = null;
    $scope.pageSize = 20;
    $scope.searchpage = '';
    $scope.finList = [];
    /* bein autocomplete*/
    var _selected;
    $scope.selected = undefined;

    $scope.getSuggPages = function (val) {
        return $http.get('autocomPages', {
            params: {
                searchpage: val

            }
        }).then(function (response) {
            console.log("response");
            console.log(response);
          //   response.data.content.map on utilise pour une page retournee
          return response.data.map(function (item) {
                return item;
            });


        });
    };
    $scope.generate = function () {
        $http.get("generate")
            .success(function(data){

                console.log("OK");
            });

}
    $scope.updateFinds = function (val) {

        console.log("commencer finlist");
        $http.get("finList",{
            params: {
                debut: val

            }
        }).then(function (response) {
            console.log("debutv="+  val);
            console.log(response);
            $scope.finList=response.data;


        });


    }
    $scope.creerDoc = function (val) {

        console.log("commencer creer Doc");
        $http.get("creerDoc",{
            params: {
                debut: val

            }
        }).then(function (response) {
            console.log("debutv="+  val);
            console.log(response);
            $scope.finList=response.data;


        });


    }
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
					 
					  $scope.getSuggPages = function(val) {
						    return $http.get('autocomPages', {
						      params: {
						    	searchpage : val
						        
						      }
						    }).then(function(response){
						    	console.log("response");
						    	console.log(response);
						      return response.data.map(function(item){
						        return item;
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

	
	
	

	
});

