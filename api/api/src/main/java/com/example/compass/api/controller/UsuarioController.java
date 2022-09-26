package com.example.compass.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.compass.api.config.validacao.ValidaRegiao;
import com.example.compass.api.controller.dto.EstadosDto;
import com.example.compass.api.controller.dto.UsuarioDto;
import com.example.compass.api.controller.form.AtualizacaoEstadosForm;
import com.example.compass.api.controller.form.AtualizacaoUsuarioForm;
import com.example.compass.api.controller.form.EstadosForm;
import com.example.compass.api.controller.form.UsuarioForm;
import com.example.compass.api.modelo.Estados;
import com.example.compass.api.modelo.Usuario;
import com.example.compass.api.repository.UsuarioRepository;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// GET
	@GetMapping
	public List<UsuarioDto> listar() {
		List<Usuario> usuario = usuarioRepository.findAll();
		return UsuarioDto.converter(usuario);
	}

	/*
	 * // GET COM ID
	 * 
	 * @GetMapping("/{id}") public ResponseEntity<UsuarioDto> detalhar(@PathVariable
	 * Long id) { Optional<Usuario> topico = usuarioRepository.findById(id); if
	 * (topico.isPresent()) { return ResponseEntity.ok(new
	 * UsuarioDto(topico.get())); } else { return ResponseEntity.notFound().build();
	 * } }
	 */

	// POST
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuider) {
		Usuario usuario = form.converter();
		usuarioRepository.save(usuario);
		URI uri = uriBuider.path("api/v1/estados/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}

	// PUT
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizacaoUsuarioForm form) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			Usuario usuario = form.atualizar(id, usuarioRepository);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// DELETE
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();

		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
