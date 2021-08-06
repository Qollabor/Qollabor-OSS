package org.qollabor.akka.actor;

import org.qollabor.akka.actor.identity.TenantUser;

/**
 * A TenantUserMessage carries a TenantUser
 */
public interface TenantUserMessage {
    TenantUser getUser();
}
