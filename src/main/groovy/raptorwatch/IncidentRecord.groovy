package raptorwatch

import groovy.transform.Synchronized

class IncidentRecord {
    private transient int incidentCount

    IncidentRecord() {
        this.incidentCount = 0
    }

    IncidentRecord(int baseCount) {
        assert baseCount >= 0
        this.incidentCount = baseCount
    }

    @Synchronized
    int getIncidentCount() {
        return incidentCount
    }

    @Synchronized
    void setIncidentCount(int incidentCount) {
        this.incidentCount = incidentCount
    }
}
