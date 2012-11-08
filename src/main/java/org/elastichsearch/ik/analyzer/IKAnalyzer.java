package org.elastichsearch.ik.analyzer;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.elastichsearch.ik.dic.Dictionary;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;

public class IKAnalyzer extends Analyzer {
	private boolean useSmart = false;
	private Settings settings;

	public IKAnalyzer() {
		this(false);
	}

	public IKAnalyzer(boolean useSmart) {
		super();
		this.seUseSmart(useSmart);
	}

	public IKAnalyzer(Settings settings) {
		this.settings=settings;
		Dictionary.getInstance().Init(settings);
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		return new IKTokenizer(reader, useSmart(),this.settings);
	}

	public void seUseSmart(boolean useSmart) {
		this.useSmart = useSmart;
	}

	public boolean useSmart() {
		return useSmart;
	}
}
