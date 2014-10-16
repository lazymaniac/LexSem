package pl.semantyk.wikiparser;

import pl.semantyk.domain.WikiUnit;

import java.util.List;

public interface NumerationParser {

    List<WikiNumeration> parse(WikiUnit tempUnit, String line);

}
