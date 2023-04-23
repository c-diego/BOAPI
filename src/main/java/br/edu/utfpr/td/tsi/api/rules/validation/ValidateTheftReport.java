package br.edu.utfpr.td.tsi.api.rules.validation;

import br.edu.utfpr.td.tsi.api.exception.InvalidDataException;
import br.edu.utfpr.td.tsi.api.model.TheftReport;

public class ValidateTheftReport {

    public static void validate(TheftReport report) throws InvalidDataException {

        if (report.getDateOfOccurrence() == null) {
            throw new InvalidDataException("Invalid dateOfOccurence");
        }

        if (report.getPeriod()== null) {
            throw new InvalidDataException("Invalid timeOfDay");
        }

        if (report.getAdress()== null) {
            throw new InvalidDataException("Invalid location");
        }

        if (report.getVehicle() == null) {
            throw new InvalidDataException("Invalid vehicle");
        }

    }

}
