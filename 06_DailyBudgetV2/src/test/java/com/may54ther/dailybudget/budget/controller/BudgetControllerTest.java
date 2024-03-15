package com.may54ther.dailybudget.budget.controller;

import com.may54ther.dailybudget.configuration.DailyBudgetApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@SpringBootTest
@ContextConfiguration(classes = { DailyBudgetApplication.class })
class BudgetControllerTest {

    @Autowired
    private BudgetController budgetController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(budgetController).build();
    }

    @Test
    void 전체_가계부_목록_조회() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/budget/list"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.forwardedUrl("budget/list"))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void 신규_가계부_등록_성공() throws Exception {
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("categoryCode", "1");
        params.add("usageDate", "2024-03-16");
        params.add("type", "IN");
        params.add("amount", "300000");
        params.add("description", "오다 주움 ㅎㅎ");

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/budget/add").params(params))
               .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
               .andExpect(MockMvcResultMatchers.redirectedUrl("/budget/list"))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    public void 가계부_삭제_성공() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/budget/delete/60"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andDo(MockMvcResultHandlers.print());
    }
}