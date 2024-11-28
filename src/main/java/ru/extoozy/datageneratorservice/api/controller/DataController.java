package ru.extoozy.datageneratorservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.extoozy.datageneratorservice.api.dto.DataDto;
import ru.extoozy.datageneratorservice.api.dto.DataTestOptionsDto;
import ru.extoozy.datageneratorservice.api.mapper.DataMapper;
import ru.extoozy.datageneratorservice.api.mapper.DataTestOptionsMapper;
import ru.extoozy.datageneratorservice.model.Data;
import ru.extoozy.datageneratorservice.model.DataTestOptions;
import ru.extoozy.datageneratorservice.service.DataService;
import ru.extoozy.datageneratorservice.service.TestDataService;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;
    private final TestDataService testDataService;

    private final DataMapper dataMapper;
    private final DataTestOptionsMapper dataTestOptionsMapper;

    @PostMapping("/send")
    public void send(@RequestBody DataDto dto) {

        Data data = dataMapper.toEntity(dto);
        dataService.send(data);
    }

    @PostMapping("/test/send")
    public void testSend(@RequestBody DataTestOptionsDto optionsDto) {
        DataTestOptions options = dataTestOptionsMapper.toEntity(optionsDto);
        testDataService.sendMessage(options);
    }
}
