package fr.raksrinana.mediaconverter.mediaprocessor;

import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.github.kokorin.jaffree.ffprobe.FFprobeResult;
import fr.raksrinana.mediaconverter.itemprocessor.AacConverter;
import java.nio.file.Path;
import java.util.List;

public class AudioToAacMediaProcessor implements MediaProcessor{
	private static final List<String> CODECS = List.of("mp3");
	
	@Override
	public boolean canHandle(FFprobeResult probeResult){
		return probeResult.getStreams().size() == 1
				&& probeResult.getStreams().stream()
				.allMatch(stream -> CODECS.contains(stream.getCodecName()));
	}
	
	@Override
	public Runnable createConvertTask(FFmpeg ffmpeg, FFprobeResult probeResult, Path input, Path output, Path temporary){
		return new AacConverter(ffmpeg, probeResult, input, output, temporary);
	}
	
	@Override
	public String getDesiredExtension(){
		return "m4a";
	}
}
