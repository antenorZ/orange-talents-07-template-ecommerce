package br.com.zup.mercadolivre.form;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploaderFake {

	public Set<String> envia(List<MultipartFile> imagens) {
		return imagens.stream().
				map(imagem -> "http:/host.com/" + imagem.getOriginalFilename()).
				collect(Collectors.toSet());
	}

}
