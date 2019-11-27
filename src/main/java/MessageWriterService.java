import de.siegmar.fastcsv.writer.CsvAppender;
import de.siegmar.fastcsv.writer.CsvWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.Synchronized;
import org.apache.commons.lang3.time.DateFormatUtils;

public class MessageWriterService
{
	private final File outFile;
	private final CsvWriter csvWriter;
	private CsvAppender appender;

	public MessageWriterService()
	{
		csvWriter = new CsvWriter();
		outFile = new File("data/data" +
			DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(System.currentTimeMillis()) + ".csv");
		try
		{
			appender = csvWriter.append(outFile, StandardCharsets.UTF_8);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Synchronized
	public void writeMinedMessage(MinedMessage message)
	{
		try
		{
			appender.appendLine(message.getServerID(), message.getChannelID(), message.getMessage());
			appender.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public void writeHeaders()
	{
		try
		{
			CsvAppender csvAppender = csvWriter.append(outFile, StandardCharsets.UTF_8);
			csvAppender.appendLine("serverID", "channelID", "message");
			csvAppender.endLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
