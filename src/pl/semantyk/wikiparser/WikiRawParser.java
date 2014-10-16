package pl.semantyk.wikiparser;

import pl.semantyk.domain.WikiUnit;

import java.util.List;

public interface WikiRawParser {

    void parse(WikiUnit tempUnit, List<String> data);

}
