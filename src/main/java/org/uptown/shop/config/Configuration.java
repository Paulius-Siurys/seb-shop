package org.uptown.shop.config;

import org.springframework.stereotype.Component;
import org.uptown.shop.util.info.*;
import org.uptown.shop.util.product.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class Configuration {
    private final NumericOption ageOption0 = new NumericOption(1, "0-17", 0);
    private final NumericOption ageOption18 = new NumericOption(2, "18-64", 18);
    private final NumericOption ageOption65 = new NumericOption(3, "65+", 65);
    private final List<NumericOption> ageOptions = Arrays.asList(ageOption0, ageOption18, ageOption65);
    private final NumericInfo ageInfo = new NumericInfo(1, "Age", ageOptions);

    private final BooleanOption studentOptionYes = new BooleanOption(4, "Yes");
    private final BooleanOption studentOptionNo = new BooleanOption(5, "No");
    private final List<BooleanOption> studentOptions = Arrays.asList(studentOptionYes, studentOptionNo);
    private final BooleanInfo studentInfo = new BooleanInfo(2, "Student", studentOptions);

    private final NumericOption incomeOption0 = new NumericOption(6, "0", 0);
    private final NumericOption incomeOption1 = new NumericOption(7, "1-12000", 1);
    private final NumericOption incomeOption12001 = new NumericOption(8, "12001-40000", 12001);
    private final NumericOption incomeOption40001 = new NumericOption(9, "40001+", 40001);
    private final List<NumericOption> incomeOptions =
            Arrays.asList(incomeOption0, incomeOption1, incomeOption12001, incomeOption40001);
    private final NumericInfo incomeInfo = new NumericInfo(3, "Income", incomeOptions);

    private final List<Info> INFO_LIST = Arrays.asList(ageInfo, studentInfo, incomeInfo);
    private final List<Option> OPTION_LIST = new ArrayList<>();
    {
        OPTION_LIST.addAll(ageOptions);
        OPTION_LIST.addAll(studentOptions);
        OPTION_LIST.addAll(incomeOptions);
    }

    private final List<Product> PRODUCT_LIST = Arrays.asList(
            new Product("Current Account", list -> getCurrentAccountRule(list)),
            new Product("Current Account Plus", list -> getCurrentAccountPlusRule(list)),
            new Product("Junior Saver Account", list -> getJuniorSaverAccountRule(list)),
            new Product("Student Account", list -> getStudentAccountRule(list)),
            new Product("Senior Account", list -> getSeniorAccountRule(list)),
            new Product("Debit Card", list -> getDebitCardRule(list)),
            new Product("Credit Card", list -> getCreditCardRule(list)),
            new Product("Gold Credit Card", list -> getGoldCreditCardRule(list))
    );

    public List<Info> getInfoList() {
        return new ArrayList<>(INFO_LIST);
    }

    public List<Option> getOptionList() {
        return new ArrayList<>(OPTION_LIST);
    }

    public List<Product> getProductList() {
        return new ArrayList<>(PRODUCT_LIST);
    }

    private boolean getGoldCreditCardRule(List<Option> selectedOptions) {
        NumericOption selectedIncomeOption = findSelectedNumericOption(selectedOptions, incomeInfo);
        NumericOption selectedAgeOption = findSelectedNumericOption(selectedOptions, ageInfo);
        return selectedIncomeOption != null  && selectedIncomeOption.compareTo(incomeOption12001) > 0
                && selectedAgeOption != null && selectedAgeOption.compareTo(ageOption0) > 0;
    }

    private boolean getCreditCardRule(List<Option> selectedOptions) {
        NumericOption selectedIncomeOption = findSelectedNumericOption(selectedOptions, incomeInfo);
        NumericOption selectedAgeOption = findSelectedNumericOption(selectedOptions, ageInfo);
        return selectedIncomeOption != null  && selectedIncomeOption.compareTo(incomeOption1) > 0
                && selectedAgeOption != null && selectedAgeOption.compareTo(ageOption0) > 0;
    }

    private boolean getDebitCardRule(List<Option> selectedOptions) {
        NumericOption selectedIncomeOption = findSelectedNumericOption(selectedOptions, incomeInfo);
        NumericOption selectedAgeOption = findSelectedNumericOption(selectedOptions, ageInfo);
        return selectedIncomeOption != null  && selectedIncomeOption.compareTo(incomeOption1) <= 0
                && selectedAgeOption != null && selectedAgeOption.compareTo(ageOption0) > 0;
    }


    private boolean getSeniorAccountRule(List<Option> selectedOptions) {
        NumericOption selectedAgeOption = findSelectedNumericOption(selectedOptions, ageInfo);
        return selectedAgeOption != null && selectedAgeOption.compareTo(ageOption65) >= 0;
    }

    private boolean getStudentAccountRule(List<Option> selectedOptions) {
        BooleanOption selectedStudentOption = findSelectedBooleanOption(selectedOptions, studentInfo);
        NumericOption selectedAgeOption = findSelectedNumericOption(selectedOptions, ageInfo);
        return selectedStudentOption != null && selectedStudentOption.equals(studentOptionYes) &&
                selectedAgeOption != null && selectedAgeOption.compareTo(ageOption0) > 0;
    }

    private boolean getJuniorSaverAccountRule(List<Option> selectedOptions) {
        NumericOption selectedAgeOption = findSelectedNumericOption(selectedOptions, ageInfo);
        return selectedAgeOption != null && selectedAgeOption.compareTo(ageOption18) < 0;
    }

    private boolean getCurrentAccountPlusRule(List<Option> selectedOptions) {
        NumericOption selectedIncomeOption = findSelectedNumericOption(selectedOptions, incomeInfo);
        NumericOption selectedAgeOption = findSelectedNumericOption(selectedOptions, ageInfo);
        return selectedIncomeOption != null  && selectedIncomeOption.compareTo(incomeOption12001) > 0
                && selectedAgeOption != null && selectedAgeOption.compareTo(ageOption0) > 0;
    }

    private boolean getCurrentAccountRule(List<Option> selectedOptions) {
        NumericOption selectedIncomeOption = findSelectedNumericOption(selectedOptions, incomeInfo);
        NumericOption selectedAgeOption = findSelectedNumericOption(selectedOptions, ageInfo);
        return selectedIncomeOption != null  && selectedIncomeOption.compareTo(incomeOption0) > 0
                && selectedAgeOption != null && selectedAgeOption.compareTo(ageOption0) > 0;
    }

    private NumericOption findSelectedNumericOption(List<Option> selectedOptions, NumericInfo info) {
        Optional<Option> selectedOptionOptional = selectedOptions.stream()
                .filter(o -> o.getInfo().getId() == info.getId())
                .findFirst();
        return selectedOptionOptional.isPresent() && selectedOptionOptional.get() instanceof NumericOption ?
                (NumericOption)selectedOptionOptional.get() : null;
    }

    private BooleanOption findSelectedBooleanOption(List<Option> selectedOptions, BooleanInfo info) {
        Optional<Option> selectedOptionOptional = selectedOptions.stream()
                .filter(o -> o.getInfo().getId() == info.getId())
                .findFirst();
        return selectedOptionOptional.isPresent() && selectedOptionOptional.get() instanceof BooleanOption ?
                (BooleanOption)selectedOptionOptional.get() : null;
    }
}
