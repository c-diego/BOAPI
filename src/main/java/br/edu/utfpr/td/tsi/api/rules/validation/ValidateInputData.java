package br.edu.utfpr.td.tsi.api.rules.validation;

import br.edu.utfpr.td.tsi.api.exception.InvalidDataException;
import br.edu.utfpr.td.tsi.api.model.Address;
import br.edu.utfpr.td.tsi.api.model.TheftReport;
import br.edu.utfpr.td.tsi.api.model.Vehicle;

public class ValidateInputData {

    public static void validateTheftReport(TheftReport report) throws InvalidDataException {

        StringBuilder builder = new StringBuilder();

        if (isNotValidString(report.getDateOfOccurrence())) {
            builder.append(" < 'dateOfOccurrence' is required > ");
        }

        if (isNotValidString(report.getPeriod())) {
            builder.append(" < 'period' is required > ");
        }
        
        builder.append(validateAddress(report.getAddress()));
        builder.append(validateVehicle(report.getVehicle()));
        
        if (!builder.isEmpty())
            throw new InvalidDataException(builder.toString());

    }

    public static String validateAddress(Address address) {

        StringBuilder builder = new StringBuilder();

        if (address.getNumber() <= 0) {
            builder.append(" < 'number' is required > ");
        }

        if (isNotValidString(address.getStreet())) {
            builder.append(" < 'street' is required' >");
        }

        if (isNotValidString(address.getNeighborhood())) {
            builder.append(" < 'neighborhood' is required' >");
        }

        if (isNotValidString(address.getCity())) {
            builder.append(" < 'city' is required' >");
        }

        if (isNotValidString(address.getState())) {
            builder.append(" < 'state' is required' >");
        }

        if (!builder.isEmpty()) {
            builder.append(" Address: ");
        }

        return builder.toString();

    }

    public static String validateVehicle(Vehicle vehicle) {

        StringBuilder builder = new StringBuilder();

        if (vehicle.getYearManufacture() < 1885) {
            builder.append(" < 'yearOfManufacture' is required and must be after 1885 > ");
        }

        if (isNotValidString(vehicle.getColor())) {
            builder.append(" < 'color' is required' >");
        }

        if (isNotValidString(vehicle.getColor())) {
            builder.append(" < 'color' is required' >");
        }

        if (isNotValidString(vehicle.getMake())) {
            builder.append(" < 'make' is required' >");
        }

        if (isNotValidString(vehicle.getType())) {
            builder.append(" < 'type' is required' >");
        }

        if (isNotValidString(vehicle.getModel())) {
            builder.append(" < 'model' is required' >");
        }

        if (!builder.isEmpty()) {
            builder.append(" Vehicle: ");
        }

        return builder.toString();
    }

    private static boolean isNotValidString(String word) {
        return (word == null || word.isEmpty());
    }

}
