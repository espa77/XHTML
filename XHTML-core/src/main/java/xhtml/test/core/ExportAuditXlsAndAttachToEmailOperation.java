package xhtml.test.core;

import org.apache.commons.lang.StringUtils;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.*;
import org.nuxeo.ecm.core.work.api.WorkManager;
import org.nuxeo.runtime.api.Framework;

/**
 *
 */
@Operation(id=ExportAuditXlsAndAttachToEmailOperation.ID, category=Constants.CAT_DOCUMENT, label="Export audit XLS and attach to email", description="Operation to export audit log as XLS file via email")
public class ExportAuditXlsAndAttachToEmailOperation {

    public static final String ID = "Audit.ExportXlsAndAttachToEmail";

    @Context
    protected CoreSession session;

    @OperationMethod
    public String run(DocumentModel doc) {

        NuxeoPrincipal nuxeoPrincipal = (NuxeoPrincipal) session.getPrincipal();
        if (nuxeoPrincipal == null || StringUtils.isEmpty(nuxeoPrincipal.getEmail())) {
            throw new NuxeoException("No User Email");
        }

        WorkManager workManager = Framework.getService(WorkManager.class);

        if (workManager == null) {
            throw new RuntimeException("No WorkManager available");
        }

//        ExportAuditXLSWork work = new ExportAuditXLSWork(nuxeoPrincipal.getEmail());
//        workManager.schedule(work);

        return nuxeoPrincipal.getEmail();
    }
}
