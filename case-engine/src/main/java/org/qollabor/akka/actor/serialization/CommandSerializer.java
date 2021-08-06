package org.qollabor.akka.actor.serialization;

import org.qollabor.cmmn.akka.command.*;
import org.qollabor.cmmn.akka.command.casefile.CreateCaseFileItem;
import org.qollabor.cmmn.akka.command.casefile.DeleteCaseFileItem;
import org.qollabor.cmmn.akka.command.casefile.ReplaceCaseFileItem;
import org.qollabor.cmmn.akka.command.casefile.UpdateCaseFileItem;
import org.qollabor.cmmn.akka.command.debug.SwitchDebugMode;
import org.qollabor.cmmn.akka.command.task.CompleteTask;
import org.qollabor.cmmn.akka.command.task.FailTask;
import org.qollabor.cmmn.akka.command.team.PutTeamMember;
import org.qollabor.cmmn.akka.command.team.RemoveTeamMember;
import org.qollabor.cmmn.akka.command.team.SetCaseTeam;
import org.qollabor.humantask.akka.command.*;
import org.qollabor.processtask.akka.command.*;
import org.qollabor.tenant.akka.command.*;
import org.qollabor.tenant.akka.command.platform.CreateTenant;
import org.qollabor.tenant.akka.command.platform.DisableTenant;
import org.qollabor.tenant.akka.command.platform.EnableTenant;
import org.qollabor.timerservice.akka.command.CancelTimer;
import org.qollabor.timerservice.akka.command.SetTimer;
import org.qollabor.timerservice.akka.command.response.TimerServiceResponse;

public class CommandSerializer extends QollaborSerializer {
    static void register() {
        addCaseCommands();
        addCasePlanCommands();
        addCaseFileCommands();
        addCaseTeamCommands();
        addHumanTaskCommands();
        addProcessActorCommands();
        addTenantCommands();
        addPlatformCommands();
        addTimerServiceCommands();
    }

    private static void addCaseCommands() {
        addManifestWrapper(StartCase.class, StartCase::new);
        addManifestWrapper(SwitchDebugMode.class, SwitchDebugMode::new);
    }

    private static void addCasePlanCommands() {
        addManifestWrapper(AddDiscretionaryItem.class, AddDiscretionaryItem::new);
        addManifestWrapper(GetDiscretionaryItems.class, GetDiscretionaryItems::new);
        addManifestWrapper(MakeCaseTransition.class, MakeCaseTransition::new);
        addManifestWrapper(MakePlanItemTransition.class, MakePlanItemTransition::new);
        addManifestWrapper(CompleteTask.class, CompleteTask::new);
        addManifestWrapper(FailTask.class, FailTask::new);
    }

    private static void addCaseFileCommands() {
        addManifestWrapper(CreateCaseFileItem.class, CreateCaseFileItem::new);
        addManifestWrapper(DeleteCaseFileItem.class, DeleteCaseFileItem::new);
        addManifestWrapper(ReplaceCaseFileItem.class, ReplaceCaseFileItem::new);
        addManifestWrapper(UpdateCaseFileItem.class, UpdateCaseFileItem::new);
    }

    private static void addCaseTeamCommands() {
        addManifestWrapper(PutTeamMember.class, PutTeamMember::new);
        addManifestWrapper(RemoveTeamMember.class, RemoveTeamMember::new);
        addManifestWrapper(SetCaseTeam.class, SetCaseTeam::new);
    }

    private static void addHumanTaskCommands() {
        addManifestWrapper(AssignTask.class, AssignTask::new);
        addManifestWrapper(ClaimTask.class, ClaimTask::new);
        addManifestWrapper(CompleteHumanTask.class, CompleteHumanTask::new);
        addManifestWrapper(DelegateTask.class, DelegateTask::new);
        addManifestWrapper(FillTaskDueDate.class, FillTaskDueDate::new);
        addManifestWrapper(RevokeTask.class, RevokeTask::new);
        addManifestWrapper(SaveTaskOutput.class, SaveTaskOutput::new);
        addManifestWrapper(ValidateTaskOutput.class, ValidateTaskOutput::new);
    }

    private static void addProcessActorCommands() {
        addManifestWrapper(StartProcess.class, StartProcess::new);
        addManifestWrapper(ResumeProcess.class, ResumeProcess::new);
        addManifestWrapper(ReactivateProcess.class, ReactivateProcess::new);
        addManifestWrapper(SuspendProcess.class, SuspendProcess::new);
        addManifestWrapper(TerminateProcess.class, TerminateProcess::new);
    }

    private static void addTenantCommands() {
        addManifestWrapper(UpsertTenantUser.class, UpsertTenantUser::new);
        addManifestWrapper(UpdateTenantUser.class, UpdateTenantUser::new);
        addManifestWrapper(ReplaceTenantUser.class, ReplaceTenantUser::new);
        addManifestWrapper(AddTenantUserRole.class, AddTenantUserRole::new);
        addManifestWrapper(RemoveTenantUserRole.class, RemoveTenantUserRole::new);
        addManifestWrapper(GetTenantOwners.class, GetTenantOwners::new);
        addManifestWrapper(ReplaceTenant.class, ReplaceTenant::new);
        addManifestWrapper(UpdateTenant.class, UpdateTenant::new);
    }

    private static void addPlatformCommands() {
        addManifestWrapper(CreateTenant.class, CreateTenant::new);
        addManifestWrapper(DisableTenant.class, DisableTenant::new);
        addManifestWrapper(EnableTenant.class, EnableTenant::new);
    }

    private static void addTimerServiceCommands() {
        addManifestWrapper(SetTimer.class, SetTimer::new);
        addManifestWrapper(CancelTimer.class, CancelTimer::new);
        addManifestWrapper(TimerServiceResponse.class, TimerServiceResponse::new);
    }
}
