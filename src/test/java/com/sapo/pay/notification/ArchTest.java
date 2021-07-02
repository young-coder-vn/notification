package com.sapo.pay.notification;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.sapo.pay.notification");

        noClasses()
            .that()
            .resideInAnyPackage("com.sapo.pay.notification.service..")
            .or()
            .resideInAnyPackage("com.sapo.pay.notification.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.sapo.pay.notification.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
