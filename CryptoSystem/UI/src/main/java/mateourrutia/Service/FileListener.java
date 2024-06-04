package mateourrutia.Service;

import java.io.File;

/**
 * Esta clase se dedica a crear un thread, y esperar cambios en un archivo.
 * Cuando se dectecten cambios, se dispara el Runnable.
 */
public class FileListener implements Runnable {
	private final 	String 		fileName;
	private 		long 		lastCheckedTime;
	private final 	Runnable 	runnable;
	private 		long 		timeSleep = 100;
	private 		boolean		coldStart = false;

	public FileListener(
			String fileName,
			Runnable runnable
	) {
		this.fileName = fileName;
		this.runnable = runnable;
	}

	public FileListener(
			long timeSleep,
			String fileName,
			Runnable runnable
	) {
		this(fileName, runnable);
		this.timeSleep = timeSleep;
	}

	public FileListener(
			long timeSleep,
			String fileName,
			boolean coldStart,
			Runnable runnable
	) {
		this(timeSleep, fileName, runnable);
		this.coldStart = coldStart;
	}

	private void checkForFileChanges() {
		File file = new File(fileName);
		long lastModified = file.lastModified();

		if (lastModified > lastCheckedTime)
		{
			System.out.println("Detected changes in '" + fileName + "' data file.");
			runnable.run();
			lastCheckedTime = lastModified;
		}
	}

	@Override
	public void run() {
		if ( coldStart )
		{
			checkForFileChanges();
			return;
		}

		while (!Thread.currentThread().isInterrupted())
		{
			checkForFileChanges();
			try {
				Thread.sleep(timeSleep);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}

		return;
	}
}
