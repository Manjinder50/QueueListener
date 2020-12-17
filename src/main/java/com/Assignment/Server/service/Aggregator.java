package com.Assignment.Server.service;

import com.Assignment.Server.model.FinalResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.LinkedHashMap;
import java.util.Map;

public class Aggregator {

    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

	static Map<String, Integer> votesByPartyName = new LinkedHashMap<>();

    public void aggregator(String data){

		FinalResponse finalResponse = null;
		try {
			//map the json output from queue to FinalResponse class
			finalResponse = objectMapper.readValue(data, FinalResponse.class);
		} catch (JsonProcessingException e) {
			LOGGER.warn("Exception while mapping to final response object"+e.getMessage());
			e.printStackTrace();
		}

		//populate the static map that holds the number of votes
		finalResponse.getFinalResponse().stream()
										.forEach(voteInfoFromQueue -> {
											String partyName = voteInfoFromQueue.getPartyName();
											Integer votes = votesByPartyName.get(partyName)!=null?(voteInfoFromQueue.getNoOfVotes()+votesByPartyName.get(partyName)):voteInfoFromQueue.getNoOfVotes();
											votesByPartyName.put(partyName,votes);
										});

		printToLogs();
    }

	@Scheduled(fixedDelay=5000)
	public void printToLogs() {
		LOGGER.info("Final output");
    	votesByPartyName.entrySet().forEach(entry->System.out.println(entry.getKey()+" total votes "+entry.getValue()));
	}
}
