package org.qollabor.cmmn.instance.sentry;

import org.qollabor.cmmn.instance.PlanItem;

import java.util.ArrayList;
import java.util.Collection;

public abstract class CriteriaListener<C extends Criterion> {
    protected final PlanItem item;
    protected final Collection<C> criteria = new ArrayList();

    protected CriteriaListener(PlanItem item) {
        this.item = item;
    }

    public abstract void satisfy(C criterion);

}
