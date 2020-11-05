package ru.job4j.algos;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class EmailHandlerTest {
    @Test
    public void whenAllEmailsOfOneUser() {
        Map<String, List<String>> data = new HashMap<>();
        data.put("user1", List.of(
                "123@ya.ru",
                "mms@gmail.com",
                "tre@yahoo.com"
        ));
        data.put("user2", List.of(
                "mrTwister@yandex.ru",
                "mms@gmail.com",
                "Jane@bing.com"
        ));
        data.put("user3", List.of(
                "123@ya.ru",
                "quadro@jo.uk",
                "tre@gmail.com",
                "uuu@barbara.po"
        ));
        data.put("user4", List.of(
                "lll@ll.li",
                "qweerty@mail.com",
                "uuu@barbara.po"
        ));
        Map<String, List<String>> expect = new HashMap<>();
        expect.put("user1", List.of(
                "123@ya.ru",
                "quadro@jo.uk",
                "tre@gmail.com",
                "uuu@barbara.po",
                "lll@ll.li",
                "qweerty@mail.com",
                "mms@gmail.com",
                "mrTwister@yandex.ru",
                "Jane@bing.com",
                "tre@yahoo.com"
        ));
        assertThat(EmailHandler.handler(data), is(expect));
    }

    @Test
    public void whenFrom5UsersGet2() {
        Map<String, List<String>> data = new HashMap<>();
        data.put("user1", List.of(
                "xxx@ya.ru",
                "foo@gmail.com",
                "lol@mail.ru"
        ));
        data.put("user2", List.of(
                "foo@gmail.com",
                "ups@pisem.net"
        ));
        data.put("user3", List.of(
                "xyz@pisem.net",
                "vasya@pupkin.com"
        ));
        data.put("user4", List.of(
                "ups@pisem.net",
                "aaa@bbb.ru"
        ));
        data.put("user5", List.of(
                "xyz@pisem.net"
        ));
        Map<String, List<String>> expect = new HashMap<>();
        expect.put("user1", List.of(
                "xxx@ya.ru",
                "foo@gmail.com",
                "ups@pisem.net",
                "aaa@bbb.ru",
                "lol@mail.ru"
        ));
        expect.put("user3", List.of(
                "xyz@pisem.net",
                "vasya@pupkin.com"
        ));
        assertThat(EmailHandler.handler(data), is(expect));
    }

    @Test
    public void whenAllUsersAreDifferent() {
        Map<String, List<String>> data = new HashMap<>();
        data.put("user1", List.of(
                "xxx@ya.ru",
                "foo@gmail.com",
                "lol@mail.ru"
        ));
        data.put("user2", List.of(
                "foo1@gmail.com",
                "ups@pisem.net"
        ));
        data.put("user3", List.of(
                "xyz@pisem.net",
                "vasya@pupkin.com"
        ));
        data.put("user4", List.of(
                "ups4@pisem.net",
                "aaa@bbb.ru"
        ));
        data.put("user5", List.of(
                "xyz2@pisem.net"
        ));
        assertThat(EmailHandler.handler(data), is(data));
    }
}