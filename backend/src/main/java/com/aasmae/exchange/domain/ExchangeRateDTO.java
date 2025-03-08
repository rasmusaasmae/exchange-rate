package com.aasmae.exchange.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "Envelope")
public class ExchangeRateDTO {


    @JacksonXmlProperty(localName = "subject")
    private String subject;

    @JacksonXmlProperty(localName = "Sender")
    private Sender sender;

    @JacksonXmlProperty(localName = "Cube")
    private CubeWrapper cubeWrapper;

    @Data
    public static class Sender {
        @JacksonXmlProperty(localName = "name")
        @JacksonXmlElementWrapper(useWrapping = false)
        private String name;
    }

    @Data
    public static class CubeWrapper {
        @JacksonXmlProperty(localName = "Cube")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<CubeDate> cubeDates;
    }

    @Data
    public static class CubeDate {
        @JacksonXmlProperty(isAttribute = true)
        private String time;

        @JacksonXmlProperty(localName = "Cube")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<CurrencyRate> rates;
    }

    @Data
    public static class CurrencyRate {
        @JacksonXmlProperty(isAttribute = true)
        private String currency;

        @JacksonXmlProperty(isAttribute = true)
        private String rate;
    }
}