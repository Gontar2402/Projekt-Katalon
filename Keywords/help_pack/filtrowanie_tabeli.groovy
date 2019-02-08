package help_pack

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class filtrowanie_tabeli {
	@Keyword
	def filtrowanie(String sciezka_tabeli, int od_kolumny_nr, int do_kolumny_nr, int z_ktorego_wiersza_wartosc) {
		for (int nr_output = od_kolumny_nr; nr_output <= do_kolumny_nr; nr_output++) {
			println(nr_output)

			int nr_wiersza = z_ktorego_wiersza_wartosc

			for (int i = 1; i < 2; i++) {
				def kolumna3 = WebUI.getText(findTestObject(sciezka_tabeli,
						[('wiersz') : nr_wiersza, ('kolumna') : nr_output]), FailureHandling.CONTINUE_ON_FAILURE)
				(new help_pack.Set_text_and_press_enter().set_text(findTestObject('hsi_pl_login/Obiekty powtarzalne/obiekty uniwersalne/uniwersalne pole input', [('nr_input') : nr_output]), kolumna3))


				(new help_pack.wait_for_text().not_Present('Proszę czekać', 10))



				def wiersz_1_kolumna_3 = WebUI.getText(findTestObject(sciezka_tabeli,
						[('wiersz') : 1, ('kolumna') : nr_output]), FailureHandling.CONTINUE_ON_FAILURE)

				assert wiersz_1_kolumna_3 == kolumna3
				(new help_pack.Set_text_and_press_enter().set_text(findTestObject('hsi_pl_login/Obiekty powtarzalne/obiekty uniwersalne/uniwersalne pole input', [('nr_input') : nr_output]), ''))
				(new help_pack.wait_for_text().not_Present('Proszę czekać', 10))

				nr_wiersza++
			}
		}
	}


	@Keyword
	def Sortowanie(String sciezka_tabeli, int od_kolumny_nr, int do_kolumny_nr) {
		for (int kolumna_tabeli = od_kolumny_nr; kolumna_tabeli <= do_kolumny_nr ; kolumna_tabeli++) {
			println(kolumna_tabeli)

			for (int i = 1; i < 2; i++) {
				(new help_pack.Wait_and_click().Clickable(findTestObject('hsi_pl_login/Obiekty powtarzalne/obiekty uniwersalne/tabela uniwersalna tytul sortowanie',
						[('kolumna_tabeli') : kolumna_tabeli]), 5))
				(new help_pack.wait_for_text().not_Present('Proszę czekać', 10))
				def kolumna = WebUI.getText(findTestObject(sciezka_tabeli,
						[('wiersz') : 1, ('kolumna') : kolumna_tabeli]), FailureHandling.CONTINUE_ON_FAILURE)

				(new help_pack.Wait_and_click().Clickable(findTestObject('hsi_pl_login/Obiekty powtarzalne/obiekty uniwersalne/tabela uniwersalna tytul sortowanie',
						[('kolumna_tabeli') : kolumna_tabeli]), 5))



				(new help_pack.wait_for_text().not_Present('Proszę czekać', 10))

				def wiersz_1_z_sortowanej_kolumny = WebUI.getText(findTestObject(sciezka_tabeli,
						[('wiersz') : 1, ('kolumna') : kolumna_tabeli]), FailureHandling.CONTINUE_ON_FAILURE)

				if(wiersz_1_z_sortowanej_kolumny != null || kolumna != null ) {
					assert wiersz_1_z_sortowanej_kolumny != kolumna
				}
			}
		}
	}
}
