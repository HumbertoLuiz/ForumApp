//package com.br.forum.repository;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.br.forum.enums.StatusTopic;
//import com.br.forum.models.Course;
//import com.br.forum.models.Reply;
//import com.br.forum.models.Role;
//import com.br.forum.models.Topic;
//import com.br.forum.models.User;
//
//@Configuration
//@Service
//public class DataLoaderHelper {
//
//    public static void loadData(UserRepository daoUser, RoleRepository daoRol,
//        CourseRepository daoCourse, TopicRepository daoTopic, ReplyRepository daoReply) {
//
//        List<Role> roleList= new ArrayList<>();
//        roleList.add(new Role(1L, "ROLE_ADMIN"));
//        roleList.add(new Role(2L, "ROLE_AUTHOR"));
//
//        daoRol.saveAll(roleList);
//
//        BCryptPasswordEncoder passEncoder= new BCryptPasswordEncoder();
//
//        Set<User> userList= new HashSet<>();
//
//        userList.add(new User(1L, "Humberto Luiz", "admin@test.org", passEncoder.encode("123"),
//            "123", daoRol.findByName("ROLE_ADMIN")));
//
//        userList.add(new User(2L, "Maria Aparecida", "maria90@test.org", passEncoder.encode("123"),
//            "123", daoRol.findByName("ROLE_AUTHOR")));
//
//        daoUser.saveAll(userList);
//
//        Set<Course> courseList= new HashSet<>();
//        courseList.add(new Course(1L, "Imersão Java", "Programação"));
//        courseList.add(new Course(2L, "Imersão Spring", "Programação"));
//        daoCourse.saveAll(courseList);
//
//        Set<Topic> topicList= new HashSet<>();
//        topicList.add(new Topic(1L, "Banco de Dados", "Não estou conseguindo instalar o MYSQL",
//            LocalDateTime.of(2023, 05, 10, 15, 43, 00), StatusTopic.SOLVED,
//            daoUser.findById(2L).get(), daoCourse.findById(1L).get()));
//        topicList.add(new Topic(2L, "Convesão", "Conversão de data e hora",
//            LocalDateTime.of(2023, 05, 10, 11, 33, 00), StatusTopic.SOLVED,
//            daoUser.findById(2L).get(), daoCourse.findById(2L).get()));
//        daoTopic.saveAll(topicList);
//
//        Set<Reply> replyList= new HashSet<>();
//        replyList.add(new Reply(1L, "teste1", false, LocalDateTime.of(2023, 05, 10, 18, 02, 00),
//            daoTopic.findById(1L).get(), daoUser.findById(2L).get()));
//        replyList.add(new Reply(2L, "teste2", false, LocalDateTime.of(2023, 05, 10, 13, 03, 00),
//            daoTopic.findById(2L).get(), daoUser.findById(2L).get()));
//        daoReply.saveAll(replyList);
//
//    }
//
//    @Bean
//    CommandLineRunner loader(UserRepository daoUser, RoleRepository daoRol,
//        CourseRepository daoCourse, TopicRepository daoTopic, ReplyRepository daoReply) {
//        return (args) -> {
//            DataLoaderHelper.loadData(daoUser, daoRol, daoCourse, daoTopic, daoReply);
//        };
//    }
//}
