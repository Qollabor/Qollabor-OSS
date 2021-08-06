package org.qollabor.cmmn.akka.event.plan.eventlistener;

import com.fasterxml.jackson.core.JsonGenerator;
import org.qollabor.akka.actor.serialization.Fields;
import org.qollabor.akka.actor.serialization.Manifest;
import org.qollabor.cmmn.akka.event.CaseEvent;
import org.qollabor.cmmn.instance.Case;
import org.qollabor.cmmn.instance.TimerEvent;
import org.qollabor.akka.actor.serialization.json.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;

@Manifest
public class TimerSet extends CaseEvent {
    private final static Logger logger = LoggerFactory.getLogger(TimerSet.class);

    private final Instant targetMoment;
    private final String timerId;
    private transient TimerEvent timerEvent;

    public TimerSet(TimerEvent timerEvent) {
        super(timerEvent.getCaseInstance());
        this.timerEvent = timerEvent;
        this.timerId = timerEvent.getId();
        this.targetMoment = timerEvent.getDefinition().getMoment(timerEvent);
    }

    public TimerSet(ValueMap json) {
        super(json);
        this.timerId = json.raw(Fields.timerId);
        this.targetMoment = Instant.parse(json.raw(Fields.targetMoment));
    }

    public Instant getTargetMoment() {
        return targetMoment;
    }

    public String getTimerId() {
        return timerId;
    }

    @Override
    public void updateState(Case actor) {
        if (timerEvent == null) {
            timerEvent = actor.getPlanItemById(getTimerId());
            if (timerEvent == null) {
                logger.error("MAJOR ERROR: Cannot recover task timerEvent for task with id " + getTimerId() + ", because the plan item cannot be found");
                return;
            }
        }
        timerEvent.updateState(this);
    }

    @Override
    public void write(JsonGenerator generator) throws IOException {
        super.writeCaseInstanceEvent(generator);
        writeField(generator, Fields.timerId, timerId);
        writeField(generator, Fields.targetMoment, targetMoment);
    }

    @Override
    public String toString() {
        return "Timer timerEvent "+getTimerId()+" has target moment " + targetMoment;
    }
}
