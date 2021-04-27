package com.igti.savetheplanetapi.savetheplanetapi.config.token;

import com.igti.savetheplanetapi.savetheplanetapi.security.UsuarioSistema;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UsuarioSistema usuarioSistema = (UsuarioSistema) authentication.getPrincipal();
		
		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("nome", usuarioSistema.getUsuario().getNome());
		addInfo.put("perfil", usuarioSistema.getUsuario().getPerfil().getCodigo());
		addInfo.put("codigo", usuarioSistema.getUsuario().getCodigo());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}

}
