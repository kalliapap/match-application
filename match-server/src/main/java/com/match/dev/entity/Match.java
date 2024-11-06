package com.match.dev.entity;

import com.match.dev.enumeration.SportEnum;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Match {

    @Id
    @SequenceGenerator(name = "match_seq", sequenceName = "match_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_seq")
    private long id;

    private String description;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @Column(name = "match_time", nullable = false)
    private LocalTime matchTime;

    @Column(name = "team_a")
    private String teamA;

    @Column(name = "team_b")
    private String teamB;

    private Integer sport;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "match_id", nullable = false)
    private List<MatchOdds> matchOdds;

    public Match() {
    }

    private Match(Match.Builder builder){
        setDescription(builder.description);
        setMatchDate(builder.matchDate);
        setMatchTime(builder.matchTime);
        setTeamA(builder.teamA);
        setTeamB(builder.teamB);
        setSport(builder.sport);
        setMatchOdds(builder.matchOdds);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public LocalTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalTime matchTime) {
        this.matchTime = matchTime;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public void setSport(SportEnum sportEnum) {
        this.sport = sportEnum.getDbValue();
    }

    public SportEnum getSport() {
        return SportEnum.fromDbValue(sport);
    }

    public List<MatchOdds> getMatchOdds() {
        return matchOdds;
    }

    public void setMatchOdds(List<MatchOdds> matchOdds) {
        this.matchOdds = matchOdds;
    }

    public static class Builder {
        private long id;
        private String description;
        private LocalDate matchDate;
        private LocalTime matchTime;
        private String teamA;
        private String teamB;
        private SportEnum sport;
        private List<MatchOdds> matchOdds;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withMatchDate(LocalDate matchDate) {
            this.matchDate = matchDate;
            return this;
        }

        public Builder withMatchTime(LocalTime matchTime) {
            this.matchTime = matchTime;
            return this;
        }

        public Builder withTeamA(String teamA) {
            this.teamA = teamA;
            return this;
        }

        public Builder withTeamB(String teamB) {
            this.teamB = teamB;
            return this;
        }

        public Builder withSport(SportEnum sport) {
            this.sport = sport;
            return this;
        }

        public Builder withMatchOdds(List<MatchOdds> matchOdds) {
            this.matchOdds = matchOdds;
            return this;
        }

        public Match build() {
            return new Match(this);
        }
    }
}
