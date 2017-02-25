package com.gardecote.springbatch.jobs;

import com.gardecote.entities.qLic;
import com.gardecote.models.qLicenceModel;
import com.gardecote.springbatch.common.LogProcessorListener;
import com.gardecote.springbatch.common.ProtocolListener;
import com.gardecote.springbatch.qLicRowMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by Dell on 09/02/2017.
 */

@EnableBatchProcessing

public class ImportLicencesConfiguration {
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
  //  @Bean
    public Job addNewImportLicences() {
        return jobs.get("ImportLicences")
                .listener(protocolListener())

                .start(step())

                .build();
    }
    private static final String OVERRIDDEN_BY_EXPRESSION = null;
//@Bean

    public Step step(){

        return stepBuilderFactory.get("step")
                .<qLicenceModel,qLic>chunk(1) //important to be one in this case to commit after every line read
                .reader(reader(OVERRIDDEN_BY_EXPRESSION))
                .processor(processor())
                .writer(writer())
                .listener(logProcessListener())
                .faultTolerant()
                .skipLimit(0) //default is set to 0
                .skip(SQLIntegrityConstraintViolationException.class)
                .skip(java.lang.Exception.class)
                .build();
    }

    // @Bean
    @StepScope
    public ItemReader<qLicenceModel> reader(@Value("#{jobParameters[pathToFile]}") String pathToFile){
        FlatFileItemReader<qLicenceModel> reader = new FlatFileItemReader<qLicenceModel>();
        reader.setLinesToSkip(1);//first line is title definition
        reader.setResource(new ClassPathResource(pathToFile));
        reader.setLineMapper(lineMapper());
        return reader;
    }
    // @Bean
    public LineMapper<qLicenceModel> lineMapper() {
        DefaultLineMapper<qLicenceModel> lineMapper = new DefaultLineMapper<qLicenceModel>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("|");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] { "typelicence", "numlic", "ancons", "balise", "calpoids", "count", "createdAt", "debaut", "finauto", "eff", "gt", "imo","kw", "larg", "longueur", "nbrhomm", "nomar", "nomnav", "numimm"," port", "puimot", "radio", "tjb", "typb", "updatedAt", "typencad", "nationIdnation", "qnavireNumimm", "qtypnavIdTypelic", "zoneIdzone"," qconcessionid"});

        BeanWrapperFieldSetMapper<qLicenceModel> fieldSetMapper = new BeanWrapperFieldSetMapper<qLicenceModel>();
        fieldSetMapper.setTargetType(qLicenceModel.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(qLicenceModelFieldSetMapper());

        return lineMapper;
    }
    // @Bean
    public SuggestedFieldSetMapper qLicenceModelFieldSetMapper() {
        return new SuggestedFieldSetMapper();
    }
    /** configure the processor related stuff */
    // @Bean
    public qLicenceItemProcessor processor() {
        return new qLicenceItemProcessor();
    }
    // @Bean
    public ItemWriter<qLic> writer() {
        return new Writer();
    }
    // end::readerwriterprocessor[]

    //@Bean
    public ProtocolListener protocolListener(){
        return new ProtocolListener();
    }

    @Bean
    public LogProcessorListener logProcessListener(){
        return new LogProcessorListener();
    }
}
