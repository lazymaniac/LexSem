package pl.semantyk.wikiparser;

import pl.semantyk.domain.WikiUnit;

import java.util.List;

public interface VarietyParser<T> {

    T parse(WikiUnit tempUnit, List<String> odmiana);

}
