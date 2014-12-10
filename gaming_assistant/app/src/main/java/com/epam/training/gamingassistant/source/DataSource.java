package com.epam.training.gamingassistant.source;

/**
 * Created by NuclearOK on 10.12.2014.
 */
public interface DataSource<Result,Params>{
    Result getResult(Params params) throws Exception;
}
