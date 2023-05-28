package com.br.forum.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.br.forum.dtos.ReplyDto;
import com.br.forum.models.Reply;

@Mapper(componentModel = "spring")
public interface ReplyMapper {

	ReplyMapper INSTANCE = Mappers.getMapper(ReplyMapper.class);

	Reply toModel(ReplyDto form);

	ReplyDto toForm(Reply model);
}
