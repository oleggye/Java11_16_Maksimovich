package by.epam.totalizator.tag.custom;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.totalizator.controller.command.impl.EarningManagement;

/**
 * Handler for custom tag Define type of currency, get currency symbol value
 * from {@link Currency}
 */
public class CurrencyFormatTag extends SimpleTagSupport {

	private static final Logger LOGGER = LogManager.getLogger(EarningManagement.class.getName());

	private static final String FORMATTER_PATTERN = "#0.00";
	private static final String RESULT_PATTERN = "%s %s";

	private String currency;
	private BigDecimal value;

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	/**
	 * Called by the container to invoke this tag
	 * 
	 * @throws JspException
	 *             If an error occurred while processing this tag.
	 * @throws IOException
	 *             If the page that (either directly or indirectly) invoked this
	 *             tag is to cease evaluation.
	 */
	@Override
	public void doTag() throws JspException, IOException {

		String result;
		String currencySymbol;
		String formattedValue = null;
		Currency currencyValue;

		if (this.currency != null && this.value != null) {
			try {
				
				formattedValue = formatValue();
				currencyValue = Currency.valueOf(this.currency);
				
			} catch (IllegalArgumentException e) {
				LOGGER.log(Level.ERROR, e);
				currencyValue = Currency.DEFAULT;
			}
			currencySymbol = currencyValue.getCurrencySymbol();
			result = String.format(RESULT_PATTERN, formattedValue, currencySymbol);
			getJspContext().getOut().write(result);
		}
		super.doTag();
	}
	
	private String formatValue(){
		NumberFormat formatter = new DecimalFormat(FORMATTER_PATTERN);
		return formatter.format(this.value);	
	}

	private enum Currency {
		BYN("Br"), RUB("₽"), UAH("₴"), USD("$"), EUR("€"), GBP("£"), CNY("元"), DEFAULT("¤");

		private Currency(String currencySymbol) {
			this.currencySymbol = currencySymbol;
		}

		private String currencySymbol;

		public String getCurrencySymbol() {
			return currencySymbol;
		}
	}
}