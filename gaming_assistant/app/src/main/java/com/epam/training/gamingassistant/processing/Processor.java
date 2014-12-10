package com.epam.training.gamingassistant.processing;


public interface Processor<ProcessingResult, Source> {
    ProcessingResult process(Source source) throws Exception;
}