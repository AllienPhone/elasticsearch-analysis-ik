package org.elastichsearch.ik.index;

import org.elastichsearch.ik.analyzer.IKAnalyzer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.elasticsearch.index.settings.IndexSettings;

public class IkAnalyzerProvider extends
		AbstractIndexAnalyzerProvider<IKAnalyzer> {
	private final IKAnalyzer analyzer;
	private ESLogger logger = null;
	private Settings settingsMode;

	@Inject
	public IkAnalyzerProvider(Index index,
			@IndexSettings Settings indexSettings, Environment env,
			@Assisted String name, @Assisted Settings settings) {
		super(index, indexSettings, name, settings);
		this.settingsMode = settings;
		analyzer = new IKAnalyzer(indexSettings, settings);
	}

	public IkAnalyzerProvider(Index index, Settings indexSettings, String name,
			Settings settings) {
		super(index, indexSettings, name, settings);
		analyzer = new IKAnalyzer(indexSettings,this.settingsMode);
	}

	public IkAnalyzerProvider(Index index, Settings indexSettings,
			String prefixSettings, String name, Settings settings) {
		super(index, indexSettings, prefixSettings, name, settings);
		analyzer = new IKAnalyzer(indexSettings,this.settingsMode);
	}

	@Override
	public IKAnalyzer get() {
		return this.analyzer;
	}
}
