package me.noroutine.jenkins.plugin.consolebadge;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.BuildBadgeAction;
import hudson.model.Run;
import jenkins.YesNoMaybe;
import jenkins.model.TransientActionFactory;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Oleksii Khilkevych on 7/14/16 for PAY.ON AG
 */
public class ThatUsefulBadgeIAlwaysMissed implements BuildBadgeAction {

    private transient Run run;

    public ThatUsefulBadgeIAlwaysMissed(Run run) {
        this.run = run;
    }

    @Override
    public String getIconFileName() {
        return "/plugin/console-badge/icons/16x16/terminal.png";
    }

    @Override
    public String getDisplayName() {
        return "Jump to Console";
    }

    @Override
    public String getUrlName() {
        return "consoleBadge";
    }

    public String getConsolePath() {
        return Integer.toString(this.run.getNumber()) + "/console";
    }

    @Extension(dynamicLoadable = YesNoMaybe.YES)
    public static class Factory extends TransientActionFactory<Run> {

        @Override
        public Class<Run> type() {
            return Run.class;
        }

        @Nonnull
        @Override
        public Collection<? extends Action> createFor(@Nonnull Run run) {
            return Collections.singleton(new ThatUsefulBadgeIAlwaysMissed(run));
        }
    }
}
