package com.own.component;

import com.own.entity.*;
import com.own.repository.*;
import com.own.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private BasketService basketService;

    @Autowired
    private Pbkdf2PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege editCatalogPrivilege
                = createPrivilegeIfNotFound("EDIT_CATALOG");
        Privilege addwarePrivilege
                = createPrivilegeIfNotFound("ADDWARE");
        Privilege edituserPrivilege
                = createPrivilegeIfNotFound("EDIT_USER");
        Privilege viewProfilePrivilege
                = createPrivilegeIfNotFound("VIEW_PROFILE");


        List<Privilege> warehouseManagerPrivileges = Arrays.asList(
                editCatalogPrivilege, editCatalogPrivilege);
        List<Privilege> adminPrivileges = Arrays.asList(
                edituserPrivilege);
        List<Privilege> userPrivileges = Arrays.asList(
                viewProfilePrivilege);
        List<Privilege> superuserPrivileges = Arrays.asList(
                editCatalogPrivilege, editCatalogPrivilege, addwarePrivilege, edituserPrivilege, viewProfilePrivilege);
        createRoleIfNotFound("WAREHOUSE_MANAGER", warehouseManagerPrivileges);
        createRoleIfNotFound("ADMIN", adminPrivileges);
        createRoleIfNotFound("USER", userPrivileges);
        createRoleIfNotFound("SUPERUSER", superuserPrivileges);
//        createRoleIfNotFound("USER", Arrays.asList(readPrivilege));

        Role warehouseManagerRole = roleRepository.findByName("WAREHOUSE_MANAGER");
        Role adminRole = roleRepository.findByName("ADMIN");
        Role superuserRole = roleRepository.findByName("SUPERUSER");


        User whmanager = new User();
        whmanager.setName("WAREHOUSE MANAGER");
        whmanager.setUsername("whmanager");
        whmanager.setPassword(passwordEncoder.encode("whmanager"));
        whmanager.setEmail("whmanager@test.com");
        whmanager.setPhoneNumber("80009998877");
        whmanager.setRoles(new HashSet<>(Collections.singletonList(warehouseManagerRole)));
        userRepository.save(whmanager);

        User admin = new User();
        admin.setName("ADMIN");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@test.com");
        admin.setPhoneNumber("97787653443");
        admin.setRoles(new HashSet<>(Collections.singletonList(adminRole)));
        userRepository.save(admin);

        User superuser = new User();
        superuser.setName("SUPERUSER");
        superuser.setUsername("superuser");
        superuser.setPassword(passwordEncoder.encode("superuser"));
        superuser.setEmail("superuser@test.com");
        superuser.setPhoneNumber("89998887733");
        superuser.setRoles(new HashSet<>(Collections.singletonList(superuserRole)));
        userRepository.save(superuser);

        Position position = new Position();
        position.setName("Sneakers");
        position.setPrice(456.50);
        position.setDescription("Comfortable");
        position.setCategory("Обувь");
        positionRepository.save(position);

        Position position1 = new Position();
        position1.setName("Hoop");
        position1.setPrice(340);
        position1.setDescription("Lightweight");
        position1.setCategory("Спортивные снаряды");
        positionRepository.save(position1);

        Position position2 = new Position();
        position2.setName("Football boots");
        position2.setPrice(580.50);
        position2.setDescription("For free far run");
        position2.setCategory("Футбол");
        positionRepository.save(position2);

        Position position3 = new Position();
        position3.setName("Hockey stick");
        position3.setPrice(365.99);
        position3.setDescription("Durable");
        position3.setCategory("Хоккей");
        positionRepository.save(position3);

        Position position4 = new Position();
        position4.setName("Basketball ball");
        position4.setPrice(280);
        position4.setDescription("Really round");
        position4.setCategory("Баскетбол");
        positionRepository.save(position4);

        Position position5 = new Position();
        position5.setName("Tatami mat");
        position5.setPrice(580);
        position5.setDescription("Soft");
        position5.setCategory("Спортивные снаряды");
        positionRepository.save(position5);

        Discount5per discount5per = new Discount5per();
        discount5per.setStackable(true);
        discountRepository.save(discount5per);

        Discount10per discount10per = new Discount10per();
        discount10per.setStackable(true);
        discountRepository.save(discount10per);

        DiscountEvery3Free discountEvery3Free = new DiscountEvery3Free();
        discountEvery3Free.setStackable(false);
        discountRepository.save(discountEvery3Free);

        DiscountPersonal discountPersonal = new DiscountPersonal();
        discountPersonal.setStackable(false);
        discountRepository.save(discountPersonal);

        DiscountGlobal discountGlobal = new DiscountGlobal();
        discountGlobal.setStackable(false);
        basketService.addGlobalDiscount(discountGlobal);
        discountRepository.save(discountGlobal);


        categoryRepository.save(new Category("Футбол"));
        categoryRepository.save(new Category("Воллейбол"));
        categoryRepository.save(new Category("Хоккей"));
        categoryRepository.save(new Category("Спортивные снаряды"));
        categoryRepository.save(new Category("Обувь"));

        Recipe recipe = new Recipe();
        recipe.setName("Макарошки с сыром");
        recipe.setDescription("Просто и надёжно");
        recipe.setIngredList(new String[]{"Колбаса:1", "Сыр:2", "Макароны:1"});
        recipeRepository.save(recipe);

        Recipe recipe2 = new Recipe();
        recipe2.setName("Чайный наборчик");
        recipe2.setDescription("На утро в самый раз");
        recipe2.setIngredList(new String[]{"Хлеб:2", "Творожная масса:1", "Чай:1"});
        recipeRepository.save(recipe2);

        Recipe recipe3 = new Recipe();
        recipe3.setName("Что-то странное");
        recipe3.setDescription("Попробуй - один раз навсегда");
        recipe3.setIngredList(new String[]{"Баклажан:2", "Хлеб:1", "Апельсин:1"});
        recipeRepository.save(recipe3);

        System.out.println(recipeRepository.findAll());

        System.out.println(categoryRepository.findAll());


        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}