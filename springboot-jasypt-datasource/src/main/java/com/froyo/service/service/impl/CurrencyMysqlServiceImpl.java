package com.froyo.service.service.impl;

import com.froyo.messages.MessagePairUtils;
import com.froyo.messages.codes.CurrencyMessageCode;
import com.froyo.service.model.dto.CurrencyRequest;
import com.froyo.service.model.dto.CurrencyResponse;
import com.froyo.service.model.entity.CurrencyEntity;
import com.froyo.service.repository.mysql.CurrencyMysqlRepository;
import com.froyo.service.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@Service("currencyMysqlServiceImpl")
public class CurrencyMysqlServiceImpl implements CurrencyService {

    private final CurrencyMysqlRepository currencyMysqlRepository;

    @Override
    public CurrencyResponse saveCurrency(final CurrencyRequest currencyRequest) {

        CurrencyResponse currencyResponse = new CurrencyResponse();

        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setCurrency(currencyRequest.getCurrency());
        currencyEntity.setFechacompleta(LocalDateTime.now());
        currencyEntity.setFecha(LocalDate.now());
        currencyEntity.setTiempo(LocalTime.now());

        currencyMysqlRepository.save(currencyEntity);
        currencyResponse.addMessagePair(MessagePairUtils.messagePair(CurrencyMessageCode.CY_1101));

        return currencyResponse;
    }

}
