package com.match.dev.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.match.dev.entity.Match;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

public class MatchOddDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1028443829130910060L;


    private long id;

    @JsonIgnore
    private Match match;

    @NotNull
    private String specifier;

    @NotNull
    private Float odd;

    public MatchOddDto() {
    }

    private MatchOddDto(Builder builder) {
        setId(builder.id);
        setMatch(builder.match);
        setSpecifier(builder.specifier);
        setOdd(builder.odd);
    }


    public long getId() {
        return id;
    }

    public void setId( long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getSpecifier() {
        return specifier;
    }

    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public float getOdd() {
        return odd;
    }

    public void setOdd(float odd) {
        this.odd = odd;
    }

    public static class Builder {

        private long id;
        private Match match;
        private String specifier;
        private float odd;

        public Builder withId(long id){
            this.id = id;
            return this;
        }

        public Builder withMatch(Match match) {
            this.match = match;
            return this;
        }

        public Builder withSpecifier(String specifier) {
            this.specifier = specifier;
            return this;
        }

        public Builder withOdd(float odd) {
            this.odd = odd;
            return this;
        }

        public MatchOddDto build() {
            return new MatchOddDto(this);
        }
    }
}


