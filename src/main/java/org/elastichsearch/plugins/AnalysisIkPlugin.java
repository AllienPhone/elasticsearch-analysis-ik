package org.elastichsearch.plugins;

import org.elastichsearch.ik.index.IkAnalysisBinderProcessor;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.plugins.AbstractPlugin;

public class AnalysisIkPlugin extends AbstractPlugin {

	public AnalysisIkPlugin() {

	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "analysis-ik";
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "analysis-ik";
	}

	@Override
	public void processModule(Module module) {
		if (module instanceof AnalysisModule) {
			AnalysisModule analysisModule = (AnalysisModule) module;
			analysisModule.addProcessor(new IkAnalysisBinderProcessor());
		}
	}
}
