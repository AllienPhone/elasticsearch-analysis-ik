package org.elastichsearch.ik.index;

import org.elasticsearch.index.analysis.AnalysisModule;

public class IkAnalysisBinderProcessor extends
		AnalysisModule.AnalysisBinderProcessor {
	@Override
	public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {

	}

	@Override
	public void processAnalyzers(AnalyzersBindings analyzersBindings) {
		analyzersBindings.processAnalyzer("ik", IkAnalyzerProvider.class);
		super.processAnalyzers(analyzersBindings);
	}
}
