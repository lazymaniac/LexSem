package pl.semantyk.utils;

public class GlobalSemaphores {
    public static final NormalizerFinished normFinished = new NormalizerFinished();
    public static ParserFinished parsFinished = new ParserFinished();
    public static final EnglishUnitFilerFinished engFinished =
            new EnglishUnitFilerFinished();
}

class ParserFinished {
}

