package com.semihbkgr.sequential.server.entity.projection;

import org.springframework.beans.factory.annotation.Value;

public interface VocabularyListInfo {

    int getId();

    String getName();

    @Value("#{target.vocabularies.size()}")
    int getCount();

}
