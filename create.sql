create table GCM3.dbo.qCapture33 (nummimm varchar(255) not null, idespece varchar(255) not null, esptype int not null, datedepart datetime2 not null, dateJour datetime2 not null, quantite int, especeTypee_enumesptype int, especeTypee_qespeceId varchar(255), jourDeb_dateJour datetime2, jourDeb_numimmJour varchar(255), jourMere_dateJour datetime2, jourMere_numimmJour varchar(255), qdoc_depart datetime2, qdoc_numImm varchar(255), primary key (nummimm, idespece, esptype, datedepart, dateJour))
create table GCM3.dbo.qCarnet33 (debutPage1 bigint not null, prefixNum varchar(5) not null, typeDoc int not null, NbrLignes int not null, NbrPages int not null, qconcession_ref_concession varchar(255), qnavire varchar(255), qprefix_prefix varchar(255), qprefix_typeDoc int, qusine varchar(255), primary key (debutPage1, prefixNum, typeDoc))
create table GCM3.dbo.qCategDeb33 (numimm varchar(255) not null, idCat int not null, dateDepart datetime2 not null, flag bit not null, cat_idcateg int, primary key (numimm, idCat, dateDepart))
create table GCM3.dbo.qCategoriesRessources33 (idcateg int not null, typeSupport int, typeconcessionConcernee_qtypeconcessionpk int, primary key (idcateg))
create table GCM3.dbo.qConcession33 (ref_concession varchar(255) not null, dateConcession datetime2, date_debut datetime2, date_fin datetime2, dureeconcession int, quotaConcession int, qconsignataire_idconsignataire int, primary key (ref_concession))
create table GCM3.dbo.qConsignataire33 (idconsignataire int not null, nomconsignataire varchar(255) not null, primary key (idconsignataire))
create table GCM3.dbo.qDoc33 (TYPE_DOC varchar(20) not null, depart datetime2 not null, numImm varchar(255) not null, gt varchar(10), bloquerDeletion bit not null, dateExpiration datetime2, debloquerModification bit not null, dureeConcession int, doctype int not null, imo varchar(10), nomcapitaine varchar(20), nomNavire varchar(255), prefix varchar(255), quota int, Retour datetime2, segPeche varchar(255), support int, typePeche int, typeJP int, typeDeb int, qteDechu bigint, licenceImputation_numlic varchar(255), qconcession_ref_concession varchar(255), qnavire_numimm varchar(255), qseq_debut varchar(255), qseq_fin varchar(255), qusine_refAgrement varchar(255), marreeAnnexe_depart datetime2, marreeAnnexe_numImm varchar(255), qQteTraitees_depart datetime2, qQteTraitees_numImm varchar(255), primary key (depart, numImm))
create table GCM3.dbo.qEnginPecheDeb33 (numimm varchar(255) not null, dateDepart datetime2 not null, EnginMar int not null, EnginDeb int not null, flag bit, maillage int, primary key (numimm, dateDepart, EnginMar, EnginDeb))
create table GCM3.dbo.qEnginPecheMar33 (numimm varchar(255) not null, dateDepart datetime2 not null, EnginMar int not null, EnginDeb int not null, flag bit, maillage int, primary key (numimm, dateDepart, EnginMar, EnginDeb))
create table GCM3.dbo.qEnginsLicence33 (maillage int not null, EnginMar int not null, EnginDeb int not null, primary key (maillage, EnginMar, EnginDeb))
create table GCM3.dbo.qEspece33 (Code_Esp varchar(255) not null, Nom_Ar varchar(50) not null, Nom_Fr varchar(50) not null, primary key (Code_Esp))
create table GCM3.dbo.qEspeceTypee33 (enumesptype int not null, qespeceId varchar(255) not null, qespece_Code_Esp varchar(255), primary key (enumesptype, qespeceId))
create table GCM3.dbo.qJourDeb33 (dateJour datetime2 not null, numimmJour varchar(255) not null, navire_numimm varchar(255), pagesDeb_typeDoc int, pagesDeb_numPage varchar(50), primary key (dateJour, numimmJour))
create table GCM3.dbo.qJourMere33 (dateJour datetime2 not null, numimmJour varchar(255) not null, nbrCaisse int not null, totalCapturs int not null, totalCong int not null, navire_numimm varchar(255), pageMarree_typeDoc int, pageMarree_numPage varchar(50), primary key (dateJour, numimmJour))
create table GCM3.dbo.qJourMereAnnexe33 (dateJour datetime2 not null, indexLigne int not null, numimmJour varchar(255) not null, designation varchar(255), poidEnKg int, presentation varchar(255), especeDebarque_Code_Esp varchar(255), navire_numimm varchar(255), pageMarree_typeDoc int, pageMarree_numPage varchar(50), primary key (dateJour, indexLigne, numimmJour))
create table GCM3.dbo.qlic33 (TYPELICENCE varchar(20) not null, numlic varchar(255) not null, ancons int, balise varchar(255), calpoids varchar(255), count varchar(255), created_at datetime2, debaut datetime2, finauto datetime2, eff varchar(255), gt float, imo int, kw float, larg varchar(255), longueur varchar(255), nbrhomm varchar(255), nomar varchar(255), nomnav varchar(255), numimm varchar(255), port varchar(255), puimot varchar(255), radio varchar(255), tjb float, typb int, updated_at datetime2, typencad int, nation_idNation int, qnavire_numimm varchar(255), qtypnav_id_typelic int, zone_IdZone int, qConcessionid varchar(255), primary key (numlic))
create table GCM3.dbo.qMarreeAnnexe33 (depart datetime2 not null, numImm varchar(255) not null, IMO varchar(255), Nationalite varchar(255), bloquerDeletion bit not null, indicatifRadio varchar(255), navireReceveur varchar(255), marreePrincipal_depart datetime2, marreePrincipal_numImm varchar(255), primary key (depart, numImm))
create table GCM3.dbo.qModelJP33 (prefixModel varchar(5) not null, typeDoc int not null, qprefix_prefix varchar(255), qprefix_typeDoc int, primary key (prefixModel, typeDoc))
create table GCM3.dbo.qNation33 (idNation int not null, designation varchar(255) not null, primary key (idNation))
create table GCM3.dbo.qNavire33 (numimm varchar(255) not null, ancons int, balise varchar(255), calpoids varchar(255), count varchar(255), eff varchar(255), gt float, imo int, kw float, larg varchar(255), longueur varchar(255), nbrhomm varchar(255), nomar varchar(255), nomnav varchar(255), port varchar(255), puimot varchar(255), radio varchar(255), tjb float, typb int, createdOn datetime2 not null, nation_idNation int, primary key (numimm))
create table GCM3.dbo.qOpTraitement33 (IdOpTraitement int not null, numeroPage varchar(255) not null, qte bigint, esp_Code_Esp varchar(255), pageTraitement_typeDoc int, pageTraitement_numPage varchar(50), primary key (IdOpTraitement, numeroPage))
create table GCM3.dbo.qPageCarnet33 (TYPE_PAGE varchar(20) not null, typeDoc int not null, numPage varchar(50) not null, etatPage int, nbrLigne int, numeroOrdrePage bigint, qcarnet_debutPage1 bigint, qcarnet_prefixNum varchar(5), qcarnet_typeDoc int, qmarreeAnexe_depart datetime2, qmarreeAnexe_numImm varchar(255), qdebarquement_depart datetime2, qdebarquement_numImm varchar(255), qmarree_depart datetime2, qmarree_numImm varchar(255), qtraitement_depart datetime2, qtraitement_numImm varchar(255), primary key (typeDoc, numPage))
create table GCM3.dbo.qPrefix33 (prefix varchar(255) not null, typeDoc int not null, information varchar(255), nbrLigneCarnet int not null, primary key (prefix, typeDoc))
create table GCM3.dbo.qQteTraites33 (depart datetime2 not null, numImm varchar(255) not null, congelee int, elaboree int, fini int, frais int, huile int, transformee int, primary key (depart, numImm))
create table GCM3.dbo.qQuantiteExportee33 (dateTraitement datetime2 not null, enumZonOrientationPeche int not null, refAgrement varchar(255) not null, qte int, primary key (dateTraitement, enumZonOrientationPeche, refAgrement))
create table GCM3.dbo.qQuotasAuthorisee33 (qcategressource varbinary(255) not null, qconcession varbinary(255) not null, qQuantiteAuthorisee double precision, primary key (qcategressource, qconcession))
create table GCM3.dbo.qSegUsines33 (dateTraitement datetime2 not null, refAgrement varchar(255) not null, segPeche int not null, autres bit not null, ceph bit not null, choix bit not null, crust bit not null, demersal bit not null, pel bit not null, primary key (dateTraitement, refAgrement, segPeche))
create table GCM3.dbo.qSeq33 (debut varchar(255) not null, fin varchar(255) not null, primary key (debut, fin))
create table GCM3.dbo.qTypeConcession33 (TYPE_CONCESSION varchar(20) not null, qtypeconcessionpk int not null, designation varchar(255), prefixModel varchar(5) not null, typeDoc int, enumAncCategRessource int, enumTypePecheHautiriere int, enumTypePecheCotiere int, prefix_prefix varchar(255), prefix_typeDoc int, primary key (qtypeconcessionpk))
create table GCM3.dbo.qTypeLic33 (id_typelic int not null, codeaut varchar(255), codelic varchar(255), descr varchar(255), primary key (id_typelic))
create table GCM3.dbo.qTypeNav33 (idqTypeNav varchar(255) not null, descr varchar(255), primary key (idqTypeNav))
create table GCM3.dbo.qUsine33 (refAgrement varchar(255) not null, address varchar(255), capaciteCongelation varchar(255), capaciteStockage varchar(255), lieuImplementation varchar(255), nomResp varchar(255), nomUsine varchar(255), refAgrementUE varchar(255), signatureCapitaine bit not null, primary key (refAgrement))
create table GCM3.dbo.qZone33 (IdZone int not null, nom varchar(255), primary key (IdZone))
alter table GCM3.dbo.qCapture33 add constraint FK99vhxequucssoja2ksvpelitq foreign key (especeTypee_enumesptype, especeTypee_qespeceId) references GCM3.dbo.qEspeceTypee33
alter table GCM3.dbo.qCapture33 add constraint FK3q4avjk727ininv42ubuc365e foreign key (jourDeb_dateJour, jourDeb_numimmJour) references GCM3.dbo.qJourDeb33
alter table GCM3.dbo.qCapture33 add constraint FK5mh8n33ejhf5gx8s8pwwncmgn foreign key (jourMere_dateJour, jourMere_numimmJour) references GCM3.dbo.qJourMere33
alter table GCM3.dbo.qCapture33 add constraint FKs8oquvadf9sx7wmwfj8soqeb7 foreign key (qdoc_depart, qdoc_numImm) references GCM3.dbo.qDoc33
alter table GCM3.dbo.qCarnet33 add constraint FKl9hrnkvxcdcbcmfu9f6qopbbt foreign key (qconcession_ref_concession) references GCM3.dbo.qConcession33
alter table GCM3.dbo.qCarnet33 add constraint FKftjdvvoufihcpttedvsyd7geo foreign key (qnavire) references GCM3.dbo.qNavire33
alter table GCM3.dbo.qCarnet33 add constraint FKet14o25nq62p47wayxowqqsq3 foreign key (qprefix_prefix, qprefix_typeDoc) references GCM3.dbo.qPrefix33
alter table GCM3.dbo.qCarnet33 add constraint FK6utxryassx7c6l547pa30u6o4 foreign key (qusine) references GCM3.dbo.qUsine33
alter table GCM3.dbo.qCategDeb33 add constraint FKm07780a96w32o3g45y5l1wfe8 foreign key (cat_idcateg) references GCM3.dbo.qCategoriesRessources33
alter table GCM3.dbo.qCategoriesRessources33 add constraint FKeb9hcm4rr7f6uc18g1cjknn2u foreign key (typeconcessionConcernee_qtypeconcessionpk) references GCM3.dbo.qTypeConcession33
alter table GCM3.dbo.qConcession33 add constraint FKm72axkp7g1qpvp2bafe0uc769 foreign key (qconsignataire_idconsignataire) references GCM3.dbo.qConsignataire33
alter table GCM3.dbo.qDoc33 add constraint FKrsdtgoyrjoy4cdmfe69a5mwqr foreign key (licenceImputation_numlic) references GCM3.dbo.qlic33
alter table GCM3.dbo.qDoc33 add constraint FK8wf5ohr7rl45sgnbmwlycqlam foreign key (qconcession_ref_concession) references GCM3.dbo.qConcession33
alter table GCM3.dbo.qDoc33 add constraint FKajs1x2qeal5bn6l1kg2onco35 foreign key (qnavire_numimm) references GCM3.dbo.qNavire33
alter table GCM3.dbo.qDoc33 add constraint FKqpas2pg38f9bfsq7i73o66em6 foreign key (qseq_debut, qseq_fin) references GCM3.dbo.qSeq33
alter table GCM3.dbo.qDoc33 add constraint FKpxxc81vwxxqs7p3mkbil26biw foreign key (qusine_refAgrement) references GCM3.dbo.qUsine33
alter table GCM3.dbo.qDoc33 add constraint FKjb6ml2xpxyyohabm236e1a2y3 foreign key (marreeAnnexe_depart, marreeAnnexe_numImm) references GCM3.dbo.qMarreeAnnexe33
alter table GCM3.dbo.qDoc33 add constraint FKg1i6xpo853oltdyu8kw74h0v0 foreign key (qQteTraitees_depart, qQteTraitees_numImm) references GCM3.dbo.qQteTraites33
alter table GCM3.dbo.qEspeceTypee33 add constraint FKf891y3p0k3s41xugh9pg6v28h foreign key (qespece_Code_Esp) references GCM3.dbo.qEspece33
alter table GCM3.dbo.qJourDeb33 add constraint FKg4w4pxty2urtslntv4pcx3sl7 foreign key (navire_numimm) references GCM3.dbo.qNavire33
alter table GCM3.dbo.qJourDeb33 add constraint FKfhiwjrjklmaxlmsiqcolpad47 foreign key (pagesDeb_typeDoc, pagesDeb_numPage) references GCM3.dbo.qPageCarnet33
alter table GCM3.dbo.qJourMere33 add constraint FKo1g060c6ptxexhcfki22qnyhf foreign key (navire_numimm) references GCM3.dbo.qNavire33
alter table GCM3.dbo.qJourMere33 add constraint FKb7rd0m780kvq9yyw65xpu1btw foreign key (pageMarree_typeDoc, pageMarree_numPage) references GCM3.dbo.qPageCarnet33
alter table GCM3.dbo.qJourMereAnnexe33 add constraint FKr5stngdf6xy542jl2lyr1m12w foreign key (especeDebarque_Code_Esp) references GCM3.dbo.qEspece33
alter table GCM3.dbo.qJourMereAnnexe33 add constraint FK7dcypk5pr9njoi2cm61o65ppl foreign key (navire_numimm) references GCM3.dbo.qNavire33
alter table GCM3.dbo.qJourMereAnnexe33 add constraint FKg8wp3wiey5cfpf8gntw0dis7x foreign key (pageMarree_typeDoc, pageMarree_numPage) references GCM3.dbo.qPageCarnet33
alter table GCM3.dbo.qlic33 add constraint FK4hw2op61p1x76thg2vffgqr1q foreign key (nation_idNation) references GCM3.dbo.qNation33
alter table GCM3.dbo.qlic33 add constraint FKkrpedlm8lb6u8qudo2ayn4x0s foreign key (qnavire_numimm) references GCM3.dbo.qNavire33
alter table GCM3.dbo.qlic33 add constraint FK96frem8scxdp20dg9lkplcq2a foreign key (qtypnav_id_typelic) references GCM3.dbo.qTypeLic33
alter table GCM3.dbo.qlic33 add constraint FK7ef7ycesbih0tcon8w7j03qnf foreign key (zone_IdZone) references GCM3.dbo.qZone33
alter table GCM3.dbo.qlic33 add constraint FK2pb3by8qdcxqarufb37564s3l foreign key (qConcessionid) references GCM3.dbo.qConcession33
alter table GCM3.dbo.qMarreeAnnexe33 add constraint FK6bh7gd32r2u9snn76mnad2erp foreign key (marreePrincipal_depart, marreePrincipal_numImm) references GCM3.dbo.qDoc33
alter table GCM3.dbo.qModelJP33 add constraint FKk454buskgnjopc9nfisra2npj foreign key (qprefix_prefix, qprefix_typeDoc) references GCM3.dbo.qPrefix33
alter table GCM3.dbo.qNavire33 add constraint FKipv4jy6n56coos37yo9mafjiq foreign key (nation_idNation) references GCM3.dbo.qNation33
alter table GCM3.dbo.qOpTraitement33 add constraint FKgwdes3de7j0g6wk54iejk98bb foreign key (esp_Code_Esp) references GCM3.dbo.qEspece33
alter table GCM3.dbo.qOpTraitement33 add constraint FK679hstrhvr5a46u9tj3xjof9q foreign key (pageTraitement_typeDoc, pageTraitement_numPage) references GCM3.dbo.qPageCarnet33
alter table GCM3.dbo.qPageCarnet33 add constraint FKdd0g3qcd4eh5870rxiehcrsx6 foreign key (qcarnet_debutPage1, qcarnet_prefixNum, qcarnet_typeDoc) references GCM3.dbo.qCarnet33
alter table GCM3.dbo.qPageCarnet33 add constraint FKqihls573wngpmv43o1pyt5rg9 foreign key (qmarreeAnexe_depart, qmarreeAnexe_numImm) references GCM3.dbo.qMarreeAnnexe33
alter table GCM3.dbo.qPageCarnet33 add constraint FKpne2s3hthjld2m5oe58u171nq foreign key (qdebarquement_depart, qdebarquement_numImm) references GCM3.dbo.qDoc33
alter table GCM3.dbo.qPageCarnet33 add constraint FKe6yynjbs3sfyla90rcw7ljbrt foreign key (qmarree_depart, qmarree_numImm) references GCM3.dbo.qDoc33
alter table GCM3.dbo.qPageCarnet33 add constraint FKr42d0nqo4iohuhvjwnbnbitjw foreign key (qtraitement_depart, qtraitement_numImm) references GCM3.dbo.qDoc33
alter table GCM3.dbo.qTypeConcession33 add constraint FKsruyo9rus6w89qgh84r4yep06 foreign key (prefix_prefix, prefix_typeDoc) references GCM3.dbo.qPrefix33
create table hibernate_sequence (next_val bigint)
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table qAssocCategRessourcesEngins (categressource_idcateg int not null, Engins_maillage int not null, Engins_EnginMar int not null, Engins_EnginDeb int not null)
create table qAssocConessionCategRessources (qconcession_ref_concession varchar(255) not null, categoriesRessources_idcateg int not null)
create table qAssocDebarqCategoriesBIS (qdeb_depart datetime2 not null, qdeb_numImm varchar(255) not null, categories_numimm varchar(255) not null, categories_idCat int not null, categories_dateDepart datetime2 not null)
create table qAssocDebarqEnginPecheBIS (qdeb_depart datetime2 not null, qdeb_numImm varchar(255) not null, Engins_numimm varchar(255) not null, Engins_dateDepart datetime2 not null, Engins_EnginMar int not null, Engins_EnginDeb int not null)
create table qAssocDebarqPagesBIS (qDebarquement_depart datetime2 not null, qDebarquement_numImm varchar(255) not null, pages_typeDoc int not null, pages_numPage varchar(50) not null)
create table qAssocLicencesCategRessources (qlicences_numlic varchar(255) not null, qcatressources_idcateg int not null)
create table qAssocMareesAnnexPages (qMarreeAnnexe_depart datetime2 not null, qMarreeAnnexe_numImm varchar(255) not null, pages_typeDoc int not null, pages_numPage varchar(50) not null)
create table qAssocMareesPagesBIS (qMarree_depart datetime2 not null, qMarree_numImm varchar(255) not null, pages_typeDoc int not null, pages_numPage varchar(50) not null)
create table qAssocMarreeEnginsBIS (qmarrees_depart datetime2 not null, qmarrees_numImm varchar(255) not null, qEngins_numimm varchar(255) not null, qEngins_dateDepart datetime2 not null, qEngins_EnginMar int not null, qEngins_EnginDeb int not null)
create table qAssocModelEspeceTypee3 (modeljp_prefixModel varchar(5) not null, modeljp_typeDoc int not null, especestypees_enumesptype int not null, especestypees_qespeceId varchar(255) not null)
create table qAssocTraitementQteExpBIS (qTraitement_depart datetime2 not null, qTraitement_numImm varchar(255) not null, qQteExp_dateTraitement datetime2 not null, qQteExp_enumZonOrientationPeche int not null, qQteExp_refAgrement varchar(255) not null)
create table qAssocTraitementSegmentsBIS (qTraitement_depart datetime2 not null, qTraitement_numImm varchar(255) not null, segs_dateTraitement datetime2 not null, segs_refAgrement varchar(255) not null, segs_segPeche int not null)
create table qAssocTraitPagesBIS (qTraitement_depart datetime2 not null, qTraitement_numImm varchar(255) not null, pagesTraitement_typeDoc int not null, pagesTraitement_numPage varchar(50) not null)
create table qEspece33_qEspeceTypee33 (qEspece_Code_Esp varchar(255) not null, qespecetypee_enumesptype int not null, qespecetypee_qespeceId varchar(255) not null)
create table qPageCarnet33_qOpTraitement33 (qPageTraitement_typeDoc int not null, qPageTraitement_numPage varchar(50) not null, opTraitements_IdOpTraitement int not null, opTraitements_numeroPage varchar(255) not null)
alter table qAssocCategRessourcesEngins add constraint FKp1fqi57iw149wiodjlqoo3scw foreign key (Engins_maillage, Engins_EnginMar, Engins_EnginDeb) references GCM3.dbo.qEnginsLicence33
alter table qAssocCategRessourcesEngins add constraint FKsqq3g9gxd0ywo8gxnf2gswxrw foreign key (categressource_idcateg) references GCM3.dbo.qCategoriesRessources33
alter table qAssocConessionCategRessources add constraint FKk3dgeblm5xyjpxyrbacf40iut foreign key (categoriesRessources_idcateg) references GCM3.dbo.qCategoriesRessources33
alter table qAssocConessionCategRessources add constraint FKl62tunpi4n0rnggne8g31i788 foreign key (qconcession_ref_concession) references GCM3.dbo.qConcession33
alter table qAssocDebarqCategoriesBIS add constraint FKgqg2hwlewooogki7js43ji4m5 foreign key (categories_numimm, categories_idCat, categories_dateDepart) references GCM3.dbo.qCategDeb33
alter table qAssocDebarqCategoriesBIS add constraint FKqi69xytpehgl4hgjc1ysi9u4x foreign key (qdeb_depart, qdeb_numImm) references GCM3.dbo.qDoc33
alter table qAssocDebarqEnginPecheBIS add constraint FKc8x34nd8l1se3jdqujgprpvxj foreign key (Engins_numimm, Engins_dateDepart, Engins_EnginMar, Engins_EnginDeb) references GCM3.dbo.qEnginPecheDeb33
alter table qAssocDebarqEnginPecheBIS add constraint FKsujmymp2go7xjlfgod6ouifs7 foreign key (qdeb_depart, qdeb_numImm) references GCM3.dbo.qDoc33
alter table qAssocDebarqPagesBIS add constraint UK_hgagekpr20613wdjrxbf2r0uc unique (pages_typeDoc, pages_numPage)
alter table qAssocDebarqPagesBIS add constraint FKp4rsxd8rstvi83fs254r8yhlx foreign key (pages_typeDoc, pages_numPage) references GCM3.dbo.qPageCarnet33
alter table qAssocDebarqPagesBIS add constraint FK6e0ls9ucgfu1x3wsp0vvl6kje foreign key (qDebarquement_depart, qDebarquement_numImm) references GCM3.dbo.qDoc33
alter table qAssocLicencesCategRessources add constraint FK5fjmr44rxnmhc662n8m0bflbt foreign key (qcatressources_idcateg) references GCM3.dbo.qCategoriesRessources33
alter table qAssocLicencesCategRessources add constraint FKl8s91bgld0614dt7ramnqmcl foreign key (qlicences_numlic) references GCM3.dbo.qlic33
alter table qAssocMareesAnnexPages add constraint UK_b17xr90kr7p6hylly0vhp3r88 unique (pages_typeDoc, pages_numPage)
alter table qAssocMareesAnnexPages add constraint FK58kek2k5ar2vljeqp97g84rk2 foreign key (pages_typeDoc, pages_numPage) references GCM3.dbo.qPageCarnet33
alter table qAssocMareesAnnexPages add constraint FK5ric4gaeq8m0v8p550f1y2kg1 foreign key (qMarreeAnnexe_depart, qMarreeAnnexe_numImm) references GCM3.dbo.qMarreeAnnexe33
alter table qAssocMareesPagesBIS add constraint UK_1df54rp7fqeer7n59k6eojbv unique (pages_typeDoc, pages_numPage)
alter table qAssocMareesPagesBIS add constraint FK5v65mh45q2n2b6na8t3n5de2s foreign key (pages_typeDoc, pages_numPage) references GCM3.dbo.qPageCarnet33
alter table qAssocMareesPagesBIS add constraint FKikupaw65kvk86cd90cm6q2vok foreign key (qMarree_depart, qMarree_numImm) references GCM3.dbo.qDoc33
alter table qAssocMarreeEnginsBIS add constraint FK88rioww5b5enb58vemnikc8p6 foreign key (qEngins_numimm, qEngins_dateDepart, qEngins_EnginMar, qEngins_EnginDeb) references GCM3.dbo.qEnginPecheMar33
alter table qAssocMarreeEnginsBIS add constraint FK69itoa7btwh28qko5hkt95b6f foreign key (qmarrees_depart, qmarrees_numImm) references GCM3.dbo.qDoc33
alter table qAssocModelEspeceTypee3 add constraint FKn009emsuu2mbdjuh4ipjunmdq foreign key (especestypees_enumesptype, especestypees_qespeceId) references GCM3.dbo.qEspeceTypee33
alter table qAssocModelEspeceTypee3 add constraint FKldy8boqfpmnw6d49ivp6iw261 foreign key (modeljp_prefixModel, modeljp_typeDoc) references GCM3.dbo.qModelJP33
alter table qAssocTraitementQteExpBIS add constraint UK_9t0is4erj7lbs3miu0l695tsf unique (qQteExp_dateTraitement, qQteExp_enumZonOrientationPeche, qQteExp_refAgrement)
alter table qAssocTraitementQteExpBIS add constraint FK6fm7qb5mdfr98krut3qty1j73 foreign key (qQteExp_dateTraitement, qQteExp_enumZonOrientationPeche, qQteExp_refAgrement) references GCM3.dbo.qQuantiteExportee33
alter table qAssocTraitementQteExpBIS add constraint FK2ej6s3a9gpmwh46lt6i9igcw4 foreign key (qTraitement_depart, qTraitement_numImm) references GCM3.dbo.qDoc33
alter table qAssocTraitementSegmentsBIS add constraint UK_nfhrurens4wd79auukoi700kt unique (segs_dateTraitement, segs_refAgrement, segs_segPeche)
alter table qAssocTraitementSegmentsBIS add constraint FKgik61bit6rbsn176rqd9uxty2 foreign key (segs_dateTraitement, segs_refAgrement, segs_segPeche) references GCM3.dbo.qSegUsines33
alter table qAssocTraitementSegmentsBIS add constraint FKe36x87lijci38xmappwfayqwf foreign key (qTraitement_depart, qTraitement_numImm) references GCM3.dbo.qDoc33
alter table qAssocTraitPagesBIS add constraint UK_9nfrtre1xjtgf16wcvxqaj57g unique (pagesTraitement_typeDoc, pagesTraitement_numPage)
alter table qAssocTraitPagesBIS add constraint FKhn0hbl07wwhctedjkdoeo15gf foreign key (pagesTraitement_typeDoc, pagesTraitement_numPage) references GCM3.dbo.qPageCarnet33
alter table qAssocTraitPagesBIS add constraint FKe4y33vw8m1kjrtunyugsa7000 foreign key (qTraitement_depart, qTraitement_numImm) references GCM3.dbo.qDoc33
alter table qEspece33_qEspeceTypee33 add constraint UK_l7lpnq6phe52l7q0glbgi9n50 unique (qespecetypee_enumesptype, qespecetypee_qespeceId)
alter table qEspece33_qEspeceTypee33 add constraint FK6a6jco2eng73dm0k2mp953p54 foreign key (qespecetypee_enumesptype, qespecetypee_qespeceId) references GCM3.dbo.qEspeceTypee33
alter table qEspece33_qEspeceTypee33 add constraint FKc4c8aatsxmacacbh8vmcxheea foreign key (qEspece_Code_Esp) references GCM3.dbo.qEspece33
alter table qPageCarnet33_qOpTraitement33 add constraint UK_5mk2k37jnpf5sagvk8n3di0uq unique (opTraitements_IdOpTraitement, opTraitements_numeroPage)
alter table qPageCarnet33_qOpTraitement33 add constraint FKbi61xuabr694udqq1ma0i7doh foreign key (opTraitements_IdOpTraitement, opTraitements_numeroPage) references GCM3.dbo.qOpTraitement33
alter table qPageCarnet33_qOpTraitement33 add constraint FKiu8ln0amfupiyhwr8i06l3jya foreign key (qPageTraitement_typeDoc, qPageTraitement_numPage) references GCM3.dbo.qPageCarnet33
