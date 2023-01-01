package app.slices.spring_boot_3_basic

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.Test

class ArchitectureTest {

    @Test
    fun domainIsIsolatedFromAdapter() {
        val importedClasses = ClassFileImporter().importPackages("app.slices.spring_boot_3_basic");

        val rule = noClasses().that().resideInAPackage("app.slices.spring_boot_3_basic.domain..")
            .should().dependOnClassesThat().resideInAPackage("app.slices.spring_boot_3_basic.adapter..")

        rule.check(importedClasses);
    }

    @Test
    fun configIsIsolatedFromAdapter() {
        val importedClasses = ClassFileImporter().importPackages("app.slices.spring_boot_3_basic");

        val rule = noClasses().that().resideInAPackage("app.slices.spring_boot_3_basic.config..")
            .should().dependOnClassesThat().resideInAPackage("app.slices.spring_boot_3_basic.adapter..")

        rule.check(importedClasses);
    }

}