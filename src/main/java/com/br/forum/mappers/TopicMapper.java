package com.br.forum.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.br.forum.dtos.TopicDto;
import com.br.forum.dtos.TopicUpdateDto;
import com.br.forum.models.Topic;

@Mapper(componentModel = "spring")
public interface TopicMapper {

	TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    Topic toModel(TopicDto form);

    Topic toModel(TopicUpdateDto form);

	TopicDto toForm(Topic model);
}
