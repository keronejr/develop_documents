package com.sportsx.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sportsx.model.Cliente;
import com.sportsx.model.RestCliente;
import com.sportsx.service.RestClienteService;

@RestController
public class RestClienteController {
	
	@Autowired
    private RestClienteService clienteService;
	
	@GetMapping("/SportsX/RESTful/listarClientes")
	public List<RestCliente> listarClientes() {
		return clienteService.recuperarTodos();
	}
	
	@GetMapping("/SportsX/RESTful/listarClientes/{tipo}/tipo")
	public List<RestCliente> listarClientesPorTipo(@PathVariable("tipo") String tipo) {
		return clienteService.recuperarPorTipo(tipo);
	}	
	
	@GetMapping("/SportsX/RESTful/cliente/{id}")
	public RestCliente recuperarClientePorId(@PathVariable("id") long id) {
		return clienteService.recuperarPorID(id);
	}
	
	@DeleteMapping("/SportsX/RESTful/cliente/{id}/delete")
	public void deleteStudent(@PathVariable("id") long id) {
		clienteService.excluir(id);
	}
	
	@PostMapping("/SportsX/RESTful/cliente")
	public ResponseEntity<RestCliente> incluirCliente(@RequestBody RestCliente cliente) {
		RestCliente cli = clienteService.salvar(cliente);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cli.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	/*
	private void initializeFileds(ModelMap model) {
		model.addAttribute("clientes", clienteService.recuperarTodos());
		model.addAttribute("tipoP", "ALL");
		model.addAttribute("pesquisa", new Pesquisa("ALL"));
		model.addAttribute("cliente", new Cliente());
	}
	
	@RequestMapping("/filtrar/{tipoP}")
    public ModelAndView listarClientesPorTipo(@PathVariable("tipoP") String tipoP, ModelMap model) {
        model.addAttribute("clientes", clienteService.recuperarPorTipo(tipoP));
        model.addAttribute("tipoP", tipoP);
        model.addAttribute("pesquisa", new Pesquisa(tipoP));
        return new ModelAndView("home", model);
    }

	
	@PostMapping("/pesquisar")
    public ModelAndView listarClientesPorTipoENomePost(@ModelAttribute("pesquisa") Pesquisa pesquisa, ModelMap model) {
		model.addAttribute("clientes", clienteService.recuperarPorTipoENome(pesquisa.getTipo(), pesquisa.getNome()));
		model.addAttribute("tipoP", pesquisa.getTipo());
		model.addAttribute("pesquisa", pesquisa);
        return new ModelAndView("home", model);
    }	
	
	@GetMapping("/cadastrar")
    public String cadastrarCliente(@ModelAttribute("cliente") Cliente cliente, RedirectAttributes attr) {
		//attr.addAttribute("cliente", cliente);
        return "/cliente/cliente";
    }
	
	@GetMapping("/cliente/{id}/editar")
	public ModelAndView editarCliente(@PathVariable("id") long id, ModelMap model) {
		model.addAttribute("cliente", clienteService.recuperarPorID(id));
		return new ModelAndView("/cliente/cliente", model);
    }
	
	@GetMapping("/cliente/{id}/excluir")
	public ModelAndView excluirCliente(@PathVariable("id") long id, ModelMap model) {
		clienteService.excluir(id);
		model.addAttribute("mensagem", "Cliente excluído com sucesso!");
		initializeFileds(model);
		return new ModelAndView("home", model);
	}
	
    @PostMapping("/cliente/salvar")
    public String salvarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/cliente/cliente";
        }

        clienteService.salvar(cliente);
        attr.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        return "redirect:/SportsX/";
    }
    
    @PutMapping("/cliente/salvar")
    public String atualizarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/cliente/cliente";
        }

        clienteService.ataulizar(cliente);
        attr.addFlashAttribute("mensagem", "Cliente atualizado com sucesso!");
        return "redirect:/SportsX/";
    }*/
}


