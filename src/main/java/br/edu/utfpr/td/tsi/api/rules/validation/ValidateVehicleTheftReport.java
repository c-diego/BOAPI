package br.edu.utfpr.td.tsi.api.rules.validation;

import br.edu.utfpr.td.tsi.api.exception.InvalidDataException;
import br.edu.utfpr.td.tsi.api.model.VehicleTheftReport;

public class ValidateVehicleTheftReport {

    public static void validate(VehicleTheftReport report) throws InvalidDataException {

        if (report.getDateOfOccurrence() == null) {
            throw new InvalidDataException("Invalid dateOfOccurence");
        }

        if (report.getTimeOfDay() == null) {
            throw new InvalidDataException("Invalid timeOfDay");
        }

        if (report.getLocation() == null) {
            throw new InvalidDataException("Invalid location");
        }

        if (report.getVehicle() == null) {
            throw new InvalidDataException("Invalid vehicle");
        }

    }

}
