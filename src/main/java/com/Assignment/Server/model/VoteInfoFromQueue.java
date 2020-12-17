package com.Assignment.Server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@ToString
public class VoteInfoFromQueue {

    @JsonProperty("party_name")
    private String partyName;

    @JsonProperty("no_votes")
    private int noOfVotes;
}
