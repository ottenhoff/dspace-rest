/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.rest.diagnose;

/**
 * More detailed description of the error, returned in the response.
 */
public enum ErrorDetail {

    SQL("SQL error");
    
    private final String detailsMessage;
    
    private ErrorDetail(final String detailsMessage) {
        this.detailsMessage = detailsMessage;
    }

    public String getDetailsMessage() {
        return detailsMessage;
    }
}
