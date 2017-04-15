package com.gardecote.data.repository.jpa;

import com.gardecote.entities.qTypeConcessionArtisanal;
import com.gardecote.entities.qTypeConcessionCotiere;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.gardecote.entities.qCategRessource;
import com.gardecote.entities.qTypeConcession;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Repository : Authprov.
 */

/**
 * Repository : Bateaucomplet.
 */

public  interface qTypeConcessionRepository extends PagingAndSortingRepository<qTypeConcession, Integer> {
    @Query("select ctg from qCategRessource ctg where ctg.typeconcessionConcernee=:typeconcession")
    public List<qCategRessource> checkTypeconcession(@Param("typeconcession") qTypeConcession typeconcession);
    @Query("select ctg from qTypeConcession ctg where ctg.prefixNum=:prefix and ctg.typeDoc=:typeDoc ")
    public List<qTypeConcession> getTypes(@Param("prefix") String prefix,@Param("typeDoc") String typeDoc);

    @Query("select ctg from qTypeConcessionArtisanal ctg ")
    public List<qTypeConcessionArtisanal>  returnTypesConcessionArtisanale();
    @Query("select ctg from qCategRessource ctg join ctg.typeconcessionConcernee typeC  where type(typeC) = qTypeConcessionArtisanal  ")
    public List<qCategRessource>  returnCategoriesArtisanale();
    @Query("select ctg from qTypeConcessionCotiere ctg where ctg.enumTypePecheCotiere =1  ")
    public List<qTypeConcessionCotiere>  returnTypesConcessionCotNonP();
    @Query("select ctg from qCategRessource ctg join ctg.typeconcessionConcernee typeC  where type(typeC) = qTypeConcessionCotiere and ctg.typeconcessionConcernee.prefix.prefix <>'PC'  ")
    public List<qCategRessource>  returnCategoriesCotNonP();
    @Query("select ctg from qTypeConcessionCotiere ctg where ctg.enumTypePecheCotiere =0  ")
    public List<qTypeConcessionCotiere>  returnTypesConcessionCotPont();
    @Query("select ctg from qCategRessource ctg join ctg.typeconcessionConcernee typeC  where type(typeC)= qTypeConcessionCotiere and ctg.typeconcessionConcernee.prefix.prefix ='PC'  ")
    public List<qCategRessource>  returnCategoriesCotPont();

}

