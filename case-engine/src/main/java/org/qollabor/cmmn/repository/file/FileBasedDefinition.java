package org.qollabor.cmmn.repository.file;

import org.qollabor.cmmn.definition.DefinitionsDocument;

class FileBasedDefinition {

    final long lastModified;
    final DefinitionsDocument contents;

    FileBasedDefinition(long lastModified, DefinitionsDocument contents) {
        this.lastModified = lastModified;
        this.contents = contents;
    }
}
