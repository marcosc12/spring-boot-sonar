package com.example.mvc;

import com.example.mvc.repository.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ManagementModulesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProviderRepository providerRepository;

    @Test
    void managementPagesRenderAndAcceptNewRecords() throws Exception {
        assertPage("/providers", "Proveedores");
        mockMvc.perform(post("/providers")
                        .param("name", "Proveedor de prueba")
                        .param("taxId", "TAX-001")
                        .param("email", "provider@example.com")
                        .param("phone", "555-0100"))
                .andExpect(status().is3xxRedirection());
        Long providerId = providerRepository.findAll().get(0).getId();
        mockMvc.perform(get("/providers/{id}/edit", providerId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("value=\"Proveedor de prueba\"")));
        mockMvc.perform(post("/providers")
                        .param("id", providerId.toString())
                        .param("name", "Proveedor actualizado")
                        .param("taxId", "TAX-001")
                        .param("email", "provider@example.com")
                        .param("phone", "555-0100"))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/providers"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Proveedor actualizado")));

        assertPage("/products", "Productos e inventario");
        mockMvc.perform(post("/products")
                        .param("name", "Producto de prueba")
                        .param("sku", "SKU-001")
                        .param("description", "Producto para pruebas")
                        .param("unitPrice", "12.50")
                        .param("stockQuantity", "4")
                        .param("providerName", "Proveedor de prueba"))
                .andExpect(status().is3xxRedirection());

        assertPage("/expenses", "Gastos");
        mockMvc.perform(post("/expenses")
                        .param("description", "Gasto de prueba")
                        .param("category", "Operación")
                        .param("amount", "25.75")
                        .param("incurredOn", "2026-11-12")
                        .param("bankAccount", "Cuenta de prueba"))
                .andExpect(status().is3xxRedirection());

        assertPage("/incomes", "Ingresos");
        mockMvc.perform(post("/incomes")
                        .param("description", "Ingreso de prueba")
                        .param("category", "Ventas")
                        .param("amount", "80.00")
                        .param("receivedOn", "2026-11-12")
                        .param("bankAccount", "Cuenta de prueba"))
                .andExpect(status().is3xxRedirection());

        assertPage("/bank-accounts", "Cuentas bancarias");
        mockMvc.perform(post("/bank-accounts")
                        .param("bankName", "Banco de prueba")
                        .param("accountName", "Cuenta de prueba")
                        .param("accountNumber", "000123")
                        .param("currency", "USD")
                        .param("balance", "100.00"))
                .andExpect(status().is3xxRedirection());
    }

    private void assertPage(String path, String heading) throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(heading)));
    }
}
