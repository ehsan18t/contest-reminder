package dev.pages.ahsan.creminder.cfoj;

import dev.pages.ahsan.creminder.main.Config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class Contest {
    private long id;
    private String name;
    private String type;
    private String phase;
    private boolean frozen;
    private long durationSeconds;
    private LocalDateTime startTimeSeconds;
    private long relativeTimeSeconds;
    private String preparedBy; //can be null
    private String websiteUrl; //can be null
    private String description; //can be null
    private int difficulty; // 1 - 5
    private String kind; //can be null
    private String icpcRegion; //can be null
    private String country; //can be null
    private String city; //can be null
    private String season; //can be null

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public long getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(long durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public LocalDateTime getStartTimeSeconds() {
        return startTimeSeconds;
    }

    public void setStartTimeSeconds(long startTimeSeconds) {
        Instant ins = Instant.ofEpochSecond(startTimeSeconds);
        this.startTimeSeconds = LocalDateTime.ofInstant(ins, ZoneId.of(Config.zoneID));
    }

    public long getRelativeTimeSeconds() {
        return relativeTimeSeconds;
    }

    public void setRelativeTimeSeconds(long relativeTimeSeconds) {
        this.relativeTimeSeconds = relativeTimeSeconds;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getIcpcRegion() {
        return icpcRegion;
    }

    public void setIcpcRegion(String icpcRegion) {
        this.icpcRegion = icpcRegion;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contest contest = (Contest) o;
        return id == contest.id &&
                frozen == contest.frozen &&
                durationSeconds == contest.durationSeconds &&
                startTimeSeconds == contest.startTimeSeconds &&
                relativeTimeSeconds == contest.relativeTimeSeconds &&
                difficulty == contest.difficulty &&
                Objects.equals(name, contest.name) &&
                Objects.equals(type, contest.type) &&
                Objects.equals(phase, contest.phase) &&
                Objects.equals(preparedBy, contest.preparedBy) &&
                Objects.equals(websiteUrl, contest.websiteUrl) &&
                Objects.equals(description, contest.description) &&
                Objects.equals(kind, contest.kind) &&
                Objects.equals(icpcRegion, contest.icpcRegion) &&
                Objects.equals(country, contest.country) &&
                Objects.equals(city, contest.city) &&
                Objects.equals(season, contest.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, phase, frozen, durationSeconds, startTimeSeconds, relativeTimeSeconds, preparedBy, websiteUrl, description, difficulty, kind, icpcRegion, country, city, season);
    }

    @Override
    public String toString() {
        return "dev.pages.ahsan.creminder.cfoj.Contest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", phase=" + phase +
                ", frozen=" + frozen +
                ", durationSeconds=" + durationSeconds +
                ", startTimeSeconds=" + startTimeSeconds +
                ", relativeTimeSeconds=" + relativeTimeSeconds +
                ", preparedBy='" + preparedBy + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", description='" + description + '\'' +
                ", difficulty=" + difficulty +
                ", kind='" + kind + '\'' +
                ", icpcRegion='" + icpcRegion + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", season='" + season + '\'' +
                '}';
    }
}
