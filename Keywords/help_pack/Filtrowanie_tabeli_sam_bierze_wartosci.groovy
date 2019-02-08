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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable

public class Filtruj {
	@Keyword
	def pobieranie_danych_do_filtrowania_oraz_filtrowanie(int nr_kolumny_do_sortowania, int liczba_sortowan) {
		def wartosc_z_komorki = new String[10]

		int nr_wiersza = 1



		'sortowanie kolumny'
		for (int i = 1; i < liczba_sortowan; i++) {
			(new help_pack.wait_for_text().not_Present('Proszę czekać...', 15))

			def a = WebUI.getText(findTestObject('tabela', [('xpath') : ('//tr[' + nr_wiersza) + ']/td['+nr_kolumny_do_sortowania+']']))

			if (a != '') {
				(wartosc_z_komorki[nr_wiersza]) = WebUI.getText(findTestObject('tabela', [('xpath') : ('//tr[' + nr_wiersza) +
					']/td['+nr_kolumny_do_sortowania+']']))

				nr_wiersza++
			} else {
				(new help_pack.Wait_and_click().Clickable(findTestObject('button', [('xpath') : '//th['+nr_kolumny_do_sortowania+']/div']), 15))
			}
		}

		nr_wiersza = 1

		if ((wartosc_z_komorki[1]) == null) {
			KeywordUtil.logInfo('##################### BRAK DANYCH DO FILTROWANIA ########################')

			WebUI.comment('Filtrowanie kolumny z wcześcniej pobranych wartości')
		}

		for (int a = 1; a < 7; a++) {
			'filtrowanie kolumny'
			if ((wartosc_z_komorki[nr_wiersza]) != null) {
				(new help_pack.Wyszukaj_w_tabeli_i_kliknij_wyszukany_wiersz_z_polem().set_text(wartosc_z_komorki[nr_wiersza],
						nr_kolumny_do_sortowania))

				nr_wiersza++
			} else {
				println('koniec danych do sortowania')
				(new help_pack.Set_text_and_press_enter().set_text(findTestObject('tabela', [('xpath') : '//th['+nr_kolumny_do_sortowania+']/input']), ""))
				a = 10
			}
		}
	}
}
